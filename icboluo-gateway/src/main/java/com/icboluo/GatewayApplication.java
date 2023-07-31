package com.icboluo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author icboluo
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

//    @Bean
//    public InetUtilsProperties inetUtilsProperties() {
//        return new InetUtilsProperties();
//    }
//
//    @Bean
//    public InetUtils inetUtils(InetUtilsProperties inetUtilsProperties) {
//        return new InetUtils(inetUtilsProperties);
//    }
}
