package com.icboluo;

import com.icboluo.component.ReadExcelEntity;
import com.icboluo.component.WriteExcelEntity;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * DataSourceAutoConfiguration.class 会自动查找
 * application.yml 或者 properties 文件里的 spring.datasource.* 相关属性并自动配置单数据源「注意这里提到的单数据源」。
 * 如果项目中禁止自动注入数据源配置，又没有手动配置数据源 启动就会报错
 *
 * @author icboluo
 */
@SpringBootApplication
/*        (
                exclude = {
                        DataSourceAutoConfiguration.class,
                        DataSourceTransactionManagerAutoConfiguration.class,
                })*/
@MapperScan("com.icboluo.mapper")
@EnableDiscoveryClient
@EnableConfigurationProperties({ReadExcelEntity.class, WriteExcelEntity.class})
@EnableFeignClients
@ComponentScan("com.icboluo.*") // 奇怪，为什么要加这个注解呢
public class NoteApplication {

    public static void main(String[] args) {
        SpringApplication.run(NoteApplication.class, args);
    }

}
