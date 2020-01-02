package org.sustudent.cherry.common.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.sustudent.cherry.common.security.properties.SecurityIgnoreProperties;


@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

  private String RESOURCE_ID = "resource_id";

  @Autowired
  private SecurityIgnoreProperties securityIgnoreProperties;

  @Autowired
  private CherryAuthenticationEntryPoint cherryAuthenticationEntryPoint;

  @Autowired(required = false)
  private URLSecurityMetadataSource urlSecurityMetadataSource;

  @Autowired(required = false)
  private CherryAccessDecisionManager cherryAccessDecisionManager;


  @Override
  public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
    resources.resourceId(RESOURCE_ID)
        .stateless(false)
        .authenticationEntryPoint(cherryAuthenticationEntryPoint);
  }

  /**
   * 该配置是保证通过 SecurityContextHolder.getContext().getAuthentication().getPrincipal()获取到的为登录
   * 用户的信息而并非是只有用户名，详细请查看 DefaultUserAuthenticationConverter#extractAuthentication。RedisTokenStore
   * 只有认证服务器中拥有，默认已经会通过Redis获取到用户的信息，故排除认证服务器。其他资源服务器若存在UserDetailsService的bean
   * 将会进行自定义配置，来保证获取到登录用户的全部信息
   */
  @Bean
  @ConditionalOnBean(value = UserDetailsService.class)
  @ConditionalOnMissingBean(value = RedisTokenStore.class)
  public JwtAccessTokenConverter jwtAccessTokenConverter(
      JwtAccessTokenConverter jwtAccessTokenConverter, UserDetailsService userDetailsService) {

    DefaultAccessTokenConverter tokenConverter = new DefaultAccessTokenConverter();
    DefaultUserAuthenticationConverter userAuthenticationConverter = new DefaultUserAuthenticationConverter();
    userAuthenticationConverter.setUserDetailsService(userDetailsService);
    tokenConverter.setUserTokenConverter(userAuthenticationConverter);

    jwtAccessTokenConverter.setAccessTokenConverter(tokenConverter);
    return jwtAccessTokenConverter;
  }


  @Override
  public void configure(HttpSecurity http) throws Exception {
    String[] ignoresUrls = securityIgnoreProperties.getUrls()
        .toArray(new String[securityIgnoreProperties.getUrls().size()]);
    http
        .httpBasic()
        .disable()
        .csrf()
        .disable()
        .authorizeRequests()
        .antMatchers(ignoresUrls)
        .permitAll()
        .anyRequest()
        .authenticated()
        .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
          @Override
          public <O extends FilterSecurityInterceptor> O postProcess(O object) {
            // 使用自定义的元数据
            if (urlSecurityMetadataSource != null) {
              object.setSecurityMetadataSource(urlSecurityMetadataSource);
            }
            // 使用自定义的决策器
            if (cherryAccessDecisionManager != null) {
              object.setAccessDecisionManager(cherryAccessDecisionManager);
            }
            return object;
          }
        })
        .and()
        .exceptionHandling().accessDeniedHandler(new CherryAccessDeniedHandler());
  }
}
