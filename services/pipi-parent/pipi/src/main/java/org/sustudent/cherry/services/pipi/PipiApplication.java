package org.sustudent.cherry.services.pipi;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"org.sustudent.cherry"})
@ComponentScan(basePackages = {"org.sustudent.cherry"})
@MapperScan("org.sustudent.cherry.**.mapper")
public class PipiApplication {

  public static void main(String[] args) {
    SpringApplication.run(PipiApplication.class, args);
  }
}
