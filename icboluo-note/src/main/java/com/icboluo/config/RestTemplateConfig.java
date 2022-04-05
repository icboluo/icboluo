package com.icboluo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author icboluo
 * @since 2020-08-27 21:08
 */
@Configuration
public class RestTemplateConfig {
    /**
     * 产生一个bean对象，将这个对象交个spring管理
     *
     * @return RestTemplate
     */
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
