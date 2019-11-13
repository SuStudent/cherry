package org.sustudent.cherry.services.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.context.request.RequestContextListener;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author yiyi.su
 * @version 1.0.0
 * @ClassName UserApplication.java
 * @Description UserApplication
 * @createTime 2019/10/20/ 19:52:00
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"org.sustudent.cherry"})
@ComponentScan(basePackages = {"org.sustudent.cherry"})
@MapperScan("org.sustudent.cherry.**.mapper")
public class UserApplication {

  @Bean
  public RequestContextListener requestContextListener() {
    return new RequestContextListener();
  }

  public static void main(String[] args) {
    SpringApplication.run(UserApplication.class,args);
  }
}
