package com.icboluo.common.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * BigDecimal 科学计数法
 *
 * @author icboluo
 * @since 2023-09-10 20:20
 */
public class BigDecimalRemoveZeroSerializer extends JsonSerializer<BigDecimal> {
    @Override
    public void serialize(BigDecimal bigDecimal, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeNumber(bigDecimal.stripTrailingZeros().toPlainString());
        // writeString 会序列化生成一个带""的数字，数字序列化应该保持原有的数字类型
//        jsonGenerator.writeString(bigDecimal.stripTrailingZeros().toPlainString());
    }
}
