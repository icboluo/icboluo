package com.icboluo.entity;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;

import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 修仙生涯(CultivationCareer)实体类
 *
 * @author icboluo
 * @since 2022-03-15 00:50:25
 */
@Data
public class CultivationCareer implements Serializable {
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
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime createTime;

    /**
     * 使用 DateUtil.allToDateTime 解析时间
     */
    public static class CustomLocalDateTimeDeserializer extends LocalDateTimeDeserializer {
        @Override
        public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            String value = p.getValueAsString();
            return com.icboluo.util.DateUtil.allToDateTime(value);
        }
    }


    @Serial
    private static final long serialVersionUID = 593617170844456503L;
}

