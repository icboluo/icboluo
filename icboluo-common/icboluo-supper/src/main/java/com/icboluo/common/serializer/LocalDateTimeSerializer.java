package com.icboluo.common.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.icboluo.util.DateUtil;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author icboluo
 * @see com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
 * @since 2023-09-10 19:44
 */
public class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {
    @Override
    public void serialize(LocalDateTime localDateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(DateUtil.dateFormat(localDateTime));
    }
}
