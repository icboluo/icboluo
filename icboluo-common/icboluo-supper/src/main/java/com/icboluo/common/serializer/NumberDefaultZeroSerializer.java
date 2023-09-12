package com.icboluo.common.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * Number 默认值为0
 *
 * @author icboluo
 * @since 2023-09-10 20:20
 */
public class NumberDefaultZeroSerializer extends JsonSerializer<Number> {

    @Override
    public void serialize(Number value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        // 不需要判断是否为null
        gen.writeNumber(0);
    }
}
