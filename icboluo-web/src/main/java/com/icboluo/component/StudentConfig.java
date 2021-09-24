package com.icboluo.component;

import com.icboluo.dataobject.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author icboluo
 * @date 2020/10/27 00:03
 */
//需要增加这个注解才可以让bean的内容被别的地方注入
@Configuration
@Slf4j
public class StudentConfig {
    @Bean
    public Student student() {
        log.warn("student config bean is init");
        return new Student(18, "xiao ming");
    }
}
