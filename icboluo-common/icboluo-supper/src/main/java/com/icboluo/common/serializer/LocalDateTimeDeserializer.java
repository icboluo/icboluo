package com.icboluo.common.serializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.icboluo.util.DateUtil;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author icboluo
 * @since 2023-09-10 19:44
 */
public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
    @Override
    public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        String text = jsonParser.getText();
        return DateUtil.allToDateTime(text);
    }
}
