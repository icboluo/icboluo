package com.icboluo.common.serializer;


import com.icboluo.object.Archives;
import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ValueSerializer;

/**
 * Archives 扁平化
 *
 * @author icboluo
 * @since 2023-09-10 20:13
 */
public class ArchivesFlatteningSerializer extends ValueSerializer<Archives> {
    @Override
    public void serialize(Archives arch, JsonGenerator jsonGenerator, SerializationContext SerializationContext) throws JacksonException {
        if (arch.getName() == null) {
            jsonGenerator.writeNull();
        } else {
            jsonGenerator.writeString(arch.getName().toString());
        }
    }
}
