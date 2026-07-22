package com.icboluo.common.serializer;

import com.icboluo.object.IdName;
import org.junit.jupiter.api.Test;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.annotation.JsonSerialize;

class ArchivesFlatteningSerializerTest {

    @Test
    void serialize() throws JacksonException {
        IdName student = new IdName();
        student.setId(18);
        student.setName("张三");
        SupperIdName people = new SupperIdName();
        people.student = student;

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(people);
        assert json.equals("""
                {"student":"张三"}""");

        people.student.setName(null);
        String jsonNull = mapper.writeValueAsString(people);
        assert jsonNull.equals("""
                {"student":null}""");
    }

    public static class SupperIdName {
        @JsonSerialize(using = ArchivesFlatteningSerializer.class)
        IdName student;
    }
}
