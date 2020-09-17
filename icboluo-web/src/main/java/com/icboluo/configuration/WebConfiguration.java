package com.icboluo.configuration;

import com.icboluo.interceptor.WebContextInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.Collections;

@Configuration
@Primary
public class WebConfiguration implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        ArrayList<String> excludeList = listExcludePathPatterns();
//        UserContextInterceptor userContextInterceptor = (UserContextInterceptor) ApplicationContextHelper.getBean(UserContextInterceptor.class);
        registry.addInterceptor(getUserContextInterceptor()).addPathPatterns(listIncludePathPatterns()).excludePathPatterns(excludeList);
    }


    /**
     * 包含路径
     *
     * @return 路径集合
     */
    private ArrayList<String> listIncludePathPatterns() {
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
    private ArrayList<String> listExcludePathPatterns() {
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
