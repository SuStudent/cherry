package org.sustudent.cherry.center.gateway;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages = {"org.sustudent.cherry"})
public class GatewayApplication {

  public static void main(String[] args) {
    SpringApplication.run(GatewayApplication.class);
  }
}
