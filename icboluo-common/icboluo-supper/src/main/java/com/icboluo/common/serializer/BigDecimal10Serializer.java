package com.icboluo.common.serializer;


import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ValueSerializer;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author icboluo
 * @since 2024-05-13 21:20
 */
public class BigDecimal10Serializer extends ValueSerializer<BigDecimal> {
    @Override
    public void serialize(BigDecimal bigDecimal, JsonGenerator jsonGenerator, SerializationContext SerializationContext) throws JacksonException {
        jsonGenerator.writeNumber(bigDecimal.setScale(10, RoundingMode.HALF_DOWN));
    }
}
