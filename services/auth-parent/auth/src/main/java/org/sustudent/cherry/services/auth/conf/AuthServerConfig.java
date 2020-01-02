package org.sustudent.cherry.services.auth.conf;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.bootstrap.encrypt.KeyProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.sustudent.cherry.common.security.exception.CherryOauthException;
import org.sustudent.cherry.services.auth.token.CherryJwtAccessTokenConverter;


@EnableAuthorizationServer
@Configuration
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private DataSource dataSource;

  @Autowired
  private KeyProperties keyProperties;

  @Autowired
  private RedisConnectionFactory redisConnectionFactory;

  @Autowired
  private UserDetailServiceImpl userDetailService;

  @Bean
  protected JwtAccessTokenConverter jwtTokenEnhancer() {
    KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(
        keyProperties.getKeyStore().getLocation(),
        keyProperties.getKeyStore().getPassword().toCharArray());
    CherryJwtAccessTokenConverter converter = new CherryJwtAccessTokenConverter();
    converter.setKeyPair(keyStoreKeyFactory.getKeyPair(keyProperties.getKeyStore().getAlias()));
    return converter;
  }

  /**
   * token 存储到Redis
   */
  @Bean
  public RedisTokenStore redisTokenStore() {
    return new RedisTokenStore(redisConnectionFactory);
  }

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    endpoints
        .tokenStore(redisTokenStore())
        .accessTokenConverter(jwtTokenEnhancer())
        .authenticationManager(authenticationManager)
        .userDetailsService(userDetailService)
        .exceptionTranslator(e -> {
          if (e instanceof OAuth2Exception) {
            OAuth2Exception oAuth2Exception = (OAuth2Exception) e;
            return ResponseEntity.status(HttpStatus.OK)
                .body(new CherryOauthException(oAuth2Exception.getMessage(),
                    oAuth2Exception.getHttpErrorCode()));
          } else {
            throw e;
          }
        });
  }


  @Bean
  public ClientDetailsService clientDetails() {
    return new JdbcClientDetailsService(dataSource);
  }

  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    clients.withClientDetails(clientDetails());
  }

  @Override
  public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {

    // 对获取Token的请求不再拦截
    oauthServer
        .tokenKeyAccess("permitAll()")
        // 验证获取Token的验证信息
        .checkTokenAccess("isAuthenticated()")
        .allowFormAuthenticationForClients();
  }
}
