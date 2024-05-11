package com.icboluo;

import com.icboluo.util.HttpUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author icboluo
 */
@SpringBootApplication
@MapperScan("com.icboluo.mapper")
@EnableScheduling
public class FundApplication {

    public static void main(String[] args) {
        HttpUtil.nacosYml();
        SpringApplication.run(FundApplication.class, args);
    }
}
