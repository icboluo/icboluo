package com.icboluo.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author icboluo
 */
@Configuration
@Primary
public class WebConfiguration22 implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getUserContextInterceptor2());
    }

    /**
     * @param converters 转换器
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        因为在所有的 HttpMessageConverter 实例集合中，StringHttpMessageConverter 要比其它的 Converter 排得靠前一些。
//        我们需要将处理 Object 类型的 HttpMessageConverter 放得靠前一些，这可以在 Configuration 类中完成
//        TODO 这个映射好像不对
        converters.add(0, new MappingJackson2HttpMessageConverter());
    }

    @Bean
    public ResponseResultInterceptor getUserContextInterceptor2() {
        return new ResponseResultInterceptor();
    }
}
