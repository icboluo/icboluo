package com.icboluo.common;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.icboluo.converter.StringToLocalDateConverter;
import com.icboluo.converter.StringToLocalDateTimeConverter;
import com.icboluo.interceptor.AuthInterceptor;
import com.icboluo.interceptor.WebContextInterceptor;
import com.icboluo.util.BeanHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author icboluo
 */
@Configuration
@Primary
public class WebConfiguration implements WebMvcConfigurer {
    /**
     * 增加拦截器
     *
     * @param registry 拦截器注册处
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 压根就不需要注入
        registry.addInterceptor(new ResponseResultInterceptor());
        registry.addInterceptor(getAuthInterceptor());

        List<String> excludeList = excludePathPatterns();
        // 从content中获取bean WebContextInterceptor webContextInterceptor = (WebContextInterceptor) ApplicationContextHelper.getBean(WebContextInterceptor.class);
        registry.addInterceptor(webContextInterceptor())
                .addPathPatterns(includePathPatterns())
                .excludePathPatterns(excludeList);
    }

    /**
     * 增加参数解析器
     *
     * @param resolvers 解析器集合
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
    }

    /**
     * @param converters 转换器集合
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 因为在所有的 HttpMessageConverter 实例集合中，StringHttpMessageConverter 要比其它的 Converter 排得靠前一些。
        // 我们需要将处理 Object 类型的 HttpMessageConverter 放得靠前一些，这可以在 Configuration 类中完成
        // TODO 这个映射好像不对
        // converters.add(0, new MappingJackson2HttpMessageConverter());

        // 需要先定义一个 convert 转换消息的对象
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();


        ValueFilter valueFilter = (ob, name, val) -> {
            if (null == val) {
                val = "";
            }
//          奇怪，为什么这个有时候生效有时候不生效
            if (val instanceof DayOfWeek dayOfWeek) {
                int value = dayOfWeek.getValue();
                val = "星期" + value;
            }
            if (val instanceof BigDecimal bd) {
                val = bd.setScale(4, RoundingMode.HALF_UP);
            }
            return val;
        };

        // 添加fastJson 的配置信息，比如：是否要格式化返回的json数据;
        FastJsonConfig fastConfig = new FastJsonConfig();
        // fastjson 默认就把属性的序列化结果排序了按照字典顺序展示
        fastConfig.setSerializerFeatures(
                // 保留map空的字段
                SerializerFeature.WriteMapNullValue,
                // 将String类型的null转成""
                SerializerFeature.WriteNullStringAsEmpty,
                // 将Number类型的null转成0
                SerializerFeature.WriteNullNumberAsZero,
                // 将List类型的null转成[]
                SerializerFeature.WriteNullListAsEmpty,
                // 将Boolean类型的null转成false
                SerializerFeature.WriteNullBooleanAsFalse,
                // 避免循环引用
                SerializerFeature.DisableCircularReferenceDetect,
                // 让map的key为string类型
                SerializerFeature.WriteNonStringKeyAsString,
                // 漂亮的格式
                SerializerFeature.PrettyFormat
        )
        ;
        // 缺点，这是过滤级别的序列化，序列化的结果取决于o1的值，如将LocalDateTime类型的o1转换为String类型的o1，后续将不会调用LocalDateTime的序列化器，因为已经不是LocalDateTime类型了
        fastConfig.setSerializeFilters(BeanHelper.localDateTimeValueFilter(),valueFilter);
        // 缺点，指定后，将不会使用@JSONField注解上的format属性，包括并不限于Date类，LocalDateTime类，LocalDate类。（慎用）
        fastConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
        fastConverter.setFastJsonConfig(fastConfig);
        fastConverter.setDefaultCharset(StandardCharsets.UTF_8);
        List<MediaType> mediaTypeList = new ArrayList<>();
        // 解决中文乱码问题，相当于在Controller上的@RequestMapping中加了个属性produces = "application/json"
        mediaTypeList.add(MediaType.APPLICATION_JSON);
        fastConverter.setSupportedMediaTypes(mediaTypeList);
        converters.add(0, fastConverter);
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToLocalDateConverter());
        registry.addConverter(new StringToLocalDateTimeConverter());
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "POST", "DELETE", "PUT", "PATCH")
                .maxAge(3600);
    }

    @Bean
    public AuthInterceptor getAuthInterceptor() {
        return new AuthInterceptor();
    }

    @Bean
    public WebContextInterceptor webContextInterceptor() {
        return new WebContextInterceptor();
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
}
