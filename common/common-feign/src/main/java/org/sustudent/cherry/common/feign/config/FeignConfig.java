package org.sustudent.cherry.common.feign.config;

import feign.RequestInterceptor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;

/**
 * @author yiyi.su
 * @version 1.0.0
 * @ClassName FeignConfig.java
 * @Description FeignConfig
 * @createTime 2019/10/21/ 17:04:00
 */
@Configuration
public class FeignConfig {

  @Bean
  @ConfigurationProperties(prefix = "security.oauth2.client")
  public ClientCredentialsResourceDetails clientCredentialsResourceDetails() {
    return new ClientCredentialsResourceDetails();
  }

  /**
   *
   * @return
   */
  @Bean
  public RequestInterceptor oauth2FeignRequestInterceptor(){
    return new OAuth2FeignRequestInterceptor(new DefaultOAuth2ClientContext(), clientCredentialsResourceDetails());
  }

  @Bean
  public OAuth2RestTemplate clientCredentialsRestTemplate() {
    return new OAuth2RestTemplate(clientCredentialsResourceDetails());
  }

}
