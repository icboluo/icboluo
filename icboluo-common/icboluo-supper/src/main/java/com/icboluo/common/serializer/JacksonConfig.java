package com.icboluo.common.serializer;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author icboluo
 * @see com.fasterxml.jackson.annotation.JsonProperty 作用仅仅是改变字段名称而已（也有增加默认值等功能）
 * @see com.fasterxml.jackson.annotation.JsonAlias 指定该字段可以由多个json的key值映射到 就是多个可以映射到一个字段（反序列化）
 * @see com.fasterxml.jackson.annotation.JsonFormat 指定java在序列化和反序列化过程中的格式化方式（包括日期、数字、枚举、时区、自定义）
 * @see org.springframework.format.annotation.DateTimeFormat java在序列化和反序列化过程中的时间格式化方式（支持多个日期格式）
 */
@Configuration
public class JacksonConfig {


    /**
     * 自定义构建器的目的就是给 ObjectMapper 设置一些属性，和下面的直接重写 ObjectMapper 功能上差不多
     *
     * @return Jackson2ObjectMapperBuilderCustomizer
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return builder -> builder
                .serializerByType(LocalDateTime.class, new LocalDateTimeSerializer())
                .deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer())
                .deserializerByType(LocalDate.class, new LocalDateDeserializer())
                ;
    }


/*    @Bean(name = "mapperObject")
    public ObjectMapper getObjectMapper() {
        ObjectMapper om = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        om.registerModule(javaTimeModule);
        return om;
    }*/
}
