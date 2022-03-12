package com.icboluo.configuration;

import com.alibaba.fastjson.serializer.ValueFilter;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.icboluo.interceptor.WebContextInterceptor;
import com.icboluo.resolver.RequestBodyParamResolver;
import com.icboluo.resolver.UserCodeResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.alibaba.fastjson.serializer.SerializerFeature.*;

@Configuration
@Primary
public class WebConfiguration implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> excludeList = excludePathPatterns();
//        UserContextInterceptor userContextInterceptor = (UserContextInterceptor) ApplicationContextHelper.getBean(UserContextInterceptor.class);
        registry.addInterceptor(getUserContextInterceptor())
                .addPathPatterns(includePathPatterns())
                .excludePathPatterns(excludeList);

    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new UserCodeResolver());
        resolvers.add(new RequestBodyParamResolver());
    }

    /**
     * 3.
     *
     * @param converters 转换器集合
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 1.需要先定义一个 convert 转换消息的对象;
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();

        //2、添加fastJson 的配置信息，比如：是否要格式化返回的json数据;
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        // 不忽略对象属性中的null值
        fastJsonConfig.setSerializerFeatures(
                PrettyFormat,
                WriteNullListAsEmpty,
                WriteNullStringAsEmpty);

        ValueFilter valueFilter = (o, s, o1) -> {
            if (null == o1) {
                o1 = "";
            }
            if (o1 instanceof LocalDateTime) {
                o1 = ((LocalDateTime) o1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }
            return o1;
        };
        fastJsonConfig.setSerializeFilters(valueFilter);
        //3、在convert中添加配置信息.
        fastConverter.setFastJsonConfig(fastJsonConfig);
        //4、将convert添加到converters当中.
        converters.add(fastConverter);
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
                "/test/**",
                "/rest/**",
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
                "/public/**",
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
