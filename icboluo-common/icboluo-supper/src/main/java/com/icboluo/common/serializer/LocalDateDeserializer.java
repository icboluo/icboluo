package com.icboluo.common.serializer;


import com.icboluo.util.DateUtil;
import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonParser;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.ValueDeserializer;

import java.time.LocalDate;

/**
 * 请注意 @RequestBody 取的是这里的反序列化
 *
 * @author icboluo
 * @see com.icboluo.common.converter.StringToLocalDateConverter URL中的取的是这里的转换器
 * @since 2023-09-10 19:55
 */
public class LocalDateDeserializer extends ValueDeserializer<LocalDate> {
    @Override
    public LocalDate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws JacksonException {
        String text = jsonParser.getText();
        if (text.contains("Z")) {
            text = text.replace("Z", "");
        }
        return DateUtil.allToDate(text);
    }
}
