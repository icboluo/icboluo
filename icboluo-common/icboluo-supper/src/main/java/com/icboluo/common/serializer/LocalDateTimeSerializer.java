package com.icboluo.common.serializer;


import com.icboluo.util.DateUtil;
import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ValueSerializer;

import java.time.LocalDateTime;

/**
 * @author icboluo
 * @see LocalDateTimeSerializer
 * @since 2023-09-10 19:44
 */
public class LocalDateTimeSerializer extends ValueSerializer<LocalDateTime> {
    @Override
    public void serialize(LocalDateTime localDateTime, JsonGenerator jsonGenerator, SerializationContext SerializationContext) throws JacksonException {
        jsonGenerator.writeString(DateUtil.dateFormat(localDateTime));
    }
}
