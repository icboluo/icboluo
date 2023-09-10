package com.icboluo.spring.accountanno.springConfiguation;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.icboluo.spring.accountanno")
@EnableAspectJAutoProxy
public class SpringConfiguation {
}
