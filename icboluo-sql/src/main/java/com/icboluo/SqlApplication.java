package com.icboluo;

import com.icboluo.util.HttpUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author icboluo
 */
@SpringBootApplication(exclude = {
//        DataSourceAutoConfiguration.class,
//        DataSourceTransactionManagerAutoConfiguration.class,
//        druid-spring-boot-starter 会再次扫描数据源
//        DruidDataSourceAutoConfigure.class
        })
@EnableDiscoveryClient
@MapperScan("com.icboluo.mapper")
public class SqlApplication {

    public static void main(String[] args) {
        HttpUtil.nacosYml();
        SpringApplication.run(SqlApplication.class, args);
    }
}
