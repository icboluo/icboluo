package com.icboluo.common.serializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.icboluo.util.DateUtil;

import java.io.IOException;
import java.time.LocalDate;

/**
 * @author icboluo
 * @since 2023-09-10 19:55
 */
public class LocalDateDeserializer extends JsonDeserializer<LocalDate> {
    @Override
    public LocalDate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        String text = jsonParser.getText();
        return DateUtil.allToDate(text);
    }
}
