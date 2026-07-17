package com.icboluo.common.serializer;


import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ValueSerializer;

/**
 * Number 默认值为0
 *
 * @author icboluo
 * @since 2023-09-10 20:20
 */
public class NumberDefaultZeroSerializer extends ValueSerializer<Number> {

    @Override
    public void serialize(Number value, JsonGenerator gen, SerializationContext serializers) throws JacksonException {
        // 不需要判断是否为null
        gen.writeNumber(0);
    }
}
