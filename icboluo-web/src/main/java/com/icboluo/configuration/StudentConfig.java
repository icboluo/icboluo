package com.icboluo.configuration;

import com.icboluo.object.StatusStudent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author icboluo
 * @since 2020/10/27 00:03
 */
//需要增加这个注解才可以让bean的内容被别的地方注入
@Configuration
@Slf4j
public class StudentConfig {
    @Bean
    public StatusStudent student() {
        log.warn("student config bean is init");
        return new StatusStudent(18, "xiao ming");
    }
}
