package com.icboluo;

import com.icboluo.component.ReadExcelEntity;
import com.icboluo.component.WriteExcelEntity;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author icboluo
 */
@SpringBootApplication
@MapperScan("com.icboluo.mapper")
@EnableDiscoveryClient
@EnableConfigurationProperties({ReadExcelEntity.class, WriteExcelEntity.class})
@EnableFeignClients
public class NoteApplication {

    public static void main(String[] args) {
        SpringApplication.run(NoteApplication.class, args);
    }

}
