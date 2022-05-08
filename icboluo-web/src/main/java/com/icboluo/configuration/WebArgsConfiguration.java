package com.icboluo.configuration;

import com.icboluo.resolver.RequestBodyParamResolver;
import com.icboluo.resolver.UserCodeResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebArgsConfiguration implements WebMvcConfigurer {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new UserCodeResolver());
        resolvers.add(new RequestBodyParamResolver());
    }
}
