package com.icboluo.component;

import com.icboluo.dataobject.Student;
import org.springframework.context.annotation.Bean;

/**
 * @author icboluo
 * @date 2020/10/27 00:03
 */

public class StudentConfig {
    @Bean
    public Student student() {
        return new Student(18, "xiao ming");
    }
}
