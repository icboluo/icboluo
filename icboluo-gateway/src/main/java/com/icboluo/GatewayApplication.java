package com.icboluo;

import com.icboluo.util.HttpUtil;
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
        HttpUtil.nacosYml();
        SpringApplication.run(GatewayApplication.class, args);
    }
}
