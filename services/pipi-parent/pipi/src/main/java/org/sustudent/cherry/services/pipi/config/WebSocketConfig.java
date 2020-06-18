package org.sustudent.cherry.services.pipi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author yiyi.su
 * @version 1.0.0
 * @ClassName WebSocketConfig.java
 * @Description WebSocketConfig
 * @createTime 2020/06/18/ 11:30:00
 */
@Configuration
public class WebSocketConfig {

  @Bean
  public ServerEndpointExporter serverEndpointExporter() {
    return new ServerEndpointExporter();
  }
}
