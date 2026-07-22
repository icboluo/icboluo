package com.icboluo.entity;


import lombok.Data;
import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonParser;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.annotation.JsonDeserialize;
import tools.jackson.databind.annotation.JsonSerialize;
import tools.jackson.databind.ext.javatime.deser.LocalDateTimeDeserializer;
import tools.jackson.databind.ext.javatime.ser.LocalDateTimeSerializer;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 修仙生涯(CultivationCareer)实体类
 *
 * @author icboluo
 * @since 2022-03-15 00:50:25
 */
@Data
public class CultivationCareer implements Serializable {
    @Serial
    private static final long serialVersionUID = 593617170844456503L;

    /**
     * id
     */
    private Integer id;
    /**
     * 玩家id
     */
    private Integer playerId;
    /**
     * 操作
     */
    private String oper;

    /**
     * 创建时间
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = CultivationCareer.CustomLocalDateTimeDeserializer.class)
    private LocalDateTime createTime;

    /**
     * 使用 DateUtil.allToDateTime 解析时间
     */
    public static class CustomLocalDateTimeDeserializer extends LocalDateTimeDeserializer {
        public CustomLocalDateTimeDeserializer() {
            super(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }

        @Override
        public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws JacksonException {
            try {
                return super.deserialize(p, ctxt);
            } catch (JacksonException e) {
                String value = p.getValueAsString();
                return com.icboluo.util.DateUtil.allToDateTime(value);
            }
        }
    }
}

