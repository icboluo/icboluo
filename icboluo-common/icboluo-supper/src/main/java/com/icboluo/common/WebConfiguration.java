package com.icboluo.common;

import com.icboluo.common.converter.StringToLocalDateConverter;
import com.icboluo.common.converter.StringToLocalDateTimeConverter;
import com.icboluo.interceptor.AuthInterceptor;
import com.icboluo.interceptor.WebInterceptor;
import com.icboluo.util.ArrayHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.function.BiPredicate;

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
        registry.addInterceptor(new AuthInterceptor());

        List<String> excludeList = excludePathPatterns();
        registry.addInterceptor(new WebInterceptor())
                .addPathPatterns(includePathPatterns())
                .excludePathPatterns(excludeList);

        // 默认拦截器 其中lang表示切换语言的参数名，但是参数传递是请求头拼接，难以使用
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
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
        // 我们需要将处理 Object 类型的 HttpMessageConverter 放得靠前一些，所以我们这里需要重新排序
//        HttpMessageConverter<?> ele7 = converters.remove(7);
//        HttpMessageConverter<?> ele6 = converters.remove(6);
//        converters.add(0, ele7);
//        converters.add(0, ele6);
        BiPredicate<HttpMessageConverter<?>,Class<MappingJackson2HttpMessageConverter>> match=(converter,clazz)->
            converter.getClass() == clazz;
        ArrayHelper.sortByArr(converters, match, new Class[]{MappingJackson2HttpMessageConverter.class});
    }

    /**
     * 增加格式化功能
     *
     * @param registry 格式化注册器
     */
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

    /**
     * 替换默认的LocalResolver
     *
     * @return LocaleResolver
     */
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(Locale.CHINESE);
        return localeResolver;
    }

//    public FastJsonHttpMessageConverter configureMessageConverters() {
//        // 需要先定义一个 convert 转换消息的对象
//        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
//
//
//        ValueFilter valueFilter = (ob, name, val) -> {
//            if (null == val) {
//                val = "";
//            }
////          奇怪，为什么这个有时候生效有时候不生效
//            if (val instanceof DayOfWeek dayOfWeek) {
//                int value = dayOfWeek.getValue();
//                val = "星期" + value;
//            }
//            if (val instanceof BigDecimal bd) {
//                val = bd.setScale(4, RoundingMode.HALF_UP);
//            }
//            return val;
//        };
//
//        // 添加fastJson 的配置信息，比如：是否要格式化返回的json数据;
//        FastJsonConfig fastConfig = new FastJsonConfig();
//        // fastjson 默认就把属性的序列化结果排序了按照字典顺序展示
//        fastConfig.setSerializerFeatures(
//                // 保留map空的字段
//                SerializerFeature.WriteMapNullValue,
//                // 将String类型的null转成""
//                SerializerFeature.WriteNullStringAsEmpty,
//                // 将Number类型的null转成0
//                SerializerFeature.WriteNullNumberAsZero,
//                // 将List类型的null转成[]
//                SerializerFeature.WriteNullListAsEmpty,
//                // 将Boolean类型的null转成false
//                SerializerFeature.WriteNullBooleanAsFalse,
//                // 避免循环引用
//                SerializerFeature.DisableCircularReferenceDetect,
//                // 让map的key为string类型
//                SerializerFeature.WriteNonStringKeyAsString,
//                // 漂亮的格式
//                SerializerFeature.PrettyFormat
//        )
//        ;
//        // 缺点，这是过滤级别的序列化，序列化的结果取决于o1的值，如将LocalDateTime类型的o1转换为String类型的o1，后续将不会调用LocalDateTime的序列化器，因为已经不是LocalDateTime类型了
//        fastConfig.setSerializeFilters(BeanUtil.localDateTimeValueFilter(), valueFilter);
//        // 缺点，指定后，将不会使用@JSONField注解上的format属性，包括并不限于Date类，LocalDateTime类，LocalDate类。（慎用）
//        fastConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
//        fastConverter.setFastJsonConfig(fastConfig);
//        fastConverter.setDefaultCharset(StandardCharsets.UTF_8);
//        List<MediaType> mediaTypeList = new ArrayList<>();
//        // 解决中文乱码问题，相当于在Controller上的@RequestMapping中加了个属性produces = "application/json"
//        mediaTypeList.add(MediaType.APPLICATION_JSON);
//        fastConverter.setSupportedMediaTypes(mediaTypeList);
//        return fastConverter;
//    }

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
