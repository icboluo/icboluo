package com.icboluo.common.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.icboluo.object.Archives;

import java.io.IOException;

/**
 * Archives 扁平化
 *
 * @author icboluo
 * @since 2023-09-10 20:13
 */
public class ArchivesFlatteningSerializer extends JsonSerializer<Archives> {
    @Override
    public void serialize(Archives arch, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (arch.getName() == null) {
            jsonGenerator.writeNull();
        } else {
            jsonGenerator.writeString(arch.getName().toString());
        }
    }
}
