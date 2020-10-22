package com.icboluo.configuration;

import com.icboluo.interceptor.WebContextInterceptor;
import com.icboluo.resolver.UserCodeResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
@Primary
public class WebConfiguration implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> excludeList = excludePathPatterns();
//        UserContextInterceptor userContextInterceptor = (UserContextInterceptor) ApplicationContextHelper.getBean(UserContextInterceptor.class);
        registry.addInterceptor(getUserContextInterceptor()).addPathPatterns(includePathPatterns()).excludePathPatterns(excludeList);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new UserCodeResolver());
    }

    /**
     * 包含路径
     *
     * @return 路径集合
     */
    private List<String> includePathPatterns() {
        String[] includeArray = {
                "/getUserName",
                "/subLedger/**",
                "/webInterceptor/**",
                "/test/**"
        };
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, includeArray);
        return list;
    }

    /**
     * 排除路径
     *
     * @return 路径集合
     */
    private List<String> excludePathPatterns() {
        String[] excludeArray = {
                "/error/**",
                "/static/**",
                "/resource/**",
                "/public/**"
        };
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, excludeArray);

        return list;
    }

    @Bean
    public WebContextInterceptor getUserContextInterceptor() {
        return new WebContextInterceptor();
    }
}
