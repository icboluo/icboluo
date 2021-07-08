package com.icboluo;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * TODO 这个jackson为什么反序列化不起作用
 * @author icboluo
 * @date 2021-19-08 21:19
 */
//@Component
public class LocalDateTimeSupport {

    @Bean
    public ObjectMapper getObjectMapper() {
        ObjectMapper om = new ObjectMapper();
        om.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        om.disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));
        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern("HH:mm:ss")));

        javaTimeModule.addDeserializer(LocalDateTime.class,new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        javaTimeModule.addDeserializer(LocalDate.class,new LocalDateDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        om.registerModule(javaTimeModule).registerModule(new ParameterNamesModule());


        om.setSerializerFactory(
                om.getSerializerFactory()
                        .withSerializerModifier(new DateBeanSerializerModifier()));
        return om;
    }


/*    @Bean(name = "OBJECT_MAPPER_BEAN")
    public ObjectMapper jsonObjectMapper() {
        return Jackson2ObjectMapperBuilder.json()
                .serializationInclusion(JsonInclude.Include.NON_NULL) // Don’t include null values
                .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS) //ISODate
                .modules(new JSR310Module())
                .build();
    }*/


    public static class DateBeanSerializerModifier extends BeanSerializerModifier {
        @Override
        public List<BeanPropertyWriter> changeProperties(SerializationConfig config,
                                                         BeanDescription beanDesc, List<BeanPropertyWriter> beanProperties) {
            for (BeanPropertyWriter beanProperty : beanProperties) {
                if (isLocalDateType(beanProperty)) {
                    beanProperty.assignSerializer(new LocalDateConverter());
                } else if (isLocalDateTimeType(beanProperty)) {
                    beanProperty.assignSerializer(new LocalDateTimeConverter());
                }
            }
            return beanProperties;
        }

        private boolean isLocalDateType(BeanPropertyWriter writer) {
            Class<?> clazz = writer.getType().getRawClass();
            return LocalDate.class.isAssignableFrom(clazz);
        }

        private boolean isLocalDateTimeType(BeanPropertyWriter writer) {
            Class<?> clazz = writer.getType().getRawClass();
            return LocalDateTime.class.isAssignableFrom(clazz);
        }

        public static class LocalDateConverter extends JsonSerializer<Object> {

            @Override
            public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                gen.writeNumber(((LocalDate) value).atStartOfDay().toInstant(ZoneOffset.of("+8")).toEpochMilli());
            }
        }

        public static class LocalDateTimeConverter extends JsonSerializer<Object> {

            @Override
            public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                gen.writeNumber(((LocalDateTime) value).toInstant(ZoneOffset.of("+8")).toEpochMilli());
            }
        }
    }
}
