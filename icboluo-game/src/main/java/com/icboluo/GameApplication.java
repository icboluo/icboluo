package com.icboluo;

import com.icboluo.util.HttpUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan("com.icboluo.mapper")
public class GameApplication {

    public static void main(String[] args) {
        HttpUtil.nacosYml();
        SpringApplication.run(GameApplication.class, args);
    }
}
