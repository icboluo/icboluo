package com.icboluo.common.serializer;


import com.icboluo.util.DateUtil;
import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonParser;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.ValueDeserializer;

import java.time.LocalDateTime;

/**
 * @author icboluo
 * @since 2023-09-10 19:44
 */
public class LocalDateTimeDeserializer extends ValueDeserializer<LocalDateTime> {
    @Override
    public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws JacksonException {
        String text = jsonParser.getText();
        return DateUtil.allToDateTime(text);
    }
}
