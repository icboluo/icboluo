package com.icboluo.configuration;


import org.springframework.context.annotation.Bean;
import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.*;
import tools.jackson.databind.ext.javatime.deser.LocalDateDeserializer;
import tools.jackson.databind.ext.javatime.deser.LocalDateTimeDeserializer;
import tools.jackson.databind.ext.javatime.ser.LocalDateSerializer;
import tools.jackson.databind.ext.javatime.ser.LocalDateTimeSerializer;
import tools.jackson.databind.ext.javatime.ser.LocalTimeSerializer;
import tools.jackson.databind.json.JsonMapper;
import tools.jackson.databind.module.SimpleModule;
import tools.jackson.databind.ser.BeanPropertyWriter;
import tools.jackson.databind.ser.BeanSerializerFactory;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * TODO 这个jackson为什么反序列化不起作用:因为这个是jackson
 *
 * @author icboluo
 * @since 2021-19-08 21:19
 */
//@Component
public class LocalDateTimeSupport {

    @Bean
    public ObjectMapper getObjectMapper() {
        SimpleModule module = new SimpleModule();
        module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        module.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        module.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));
        module.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern("HH:mm:ss")));
        module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        module.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        return JsonMapper.builder()
                .addModule(module)
                .serializerFactory(BeanSerializerFactory.instance.withSerializerModifier(new DateBeanSerializerModifier()))
                .build(); }


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

        public static class LocalDateConverter extends ValueSerializer<Object> {

            @Override
            public void serialize(Object value, JsonGenerator gen, SerializationContext serializers) throws IOException {
                gen.writeNumber(((LocalDate) value).atStartOfDay().toInstant(ZoneOffset.of("+8")).toEpochMilli());
            }
        }

        public static class LocalDateTimeConverter extends ValueSerializer<Object> {

            @Override
            public void serialize(Object value, JsonGenerator gen, SerializationContext serializers) throws IOException {
                gen.writeNumber(((LocalDateTime) value).toInstant(ZoneOffset.of("+8")).toEpochMilli());
            }
        }
    }
}
