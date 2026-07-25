package com.icboluo;

import com.icboluo.util.HttpUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author icboluo
 */
@SpringBootApplication
@EnableScheduling
@MapperScan("com.icboluo.mapper")
@EnableAsync
public class StockApplication {

    public static void main(String[] args) {
        HttpUtil.nacosYml();
        SpringApplication.run(StockApplication.class, args);
    }
}
