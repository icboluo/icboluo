package com.icboluo.common.serializer;


import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ValueSerializer;

import java.math.BigDecimal;

/**
 * BigDecimal 科学计数法
 *
 * @author icboluo
 * @since 2023-09-10 20:20
 */
public class BigDecimalRemoveZeroSerializer extends ValueSerializer<BigDecimal> {
    @Override
    public void serialize(BigDecimal bigDecimal, JsonGenerator jsonGenerator, SerializationContext SerializationContext) throws JacksonException {
        jsonGenerator.writeNumber(bigDecimal.stripTrailingZeros().toPlainString());
        // writeString 会序列化生成一个带""的数字，数字序列化应该保持原有的数字类型
//        jsonGenerator.writeString(bigDecimal.stripTrailingZeros().toPlainString());
    }
}
