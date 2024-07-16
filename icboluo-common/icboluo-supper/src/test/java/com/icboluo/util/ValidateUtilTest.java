package com.icboluo.util;

import com.icboluo.BaseTest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.constraints.NotEmpty;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ValidateUtilTest extends BaseTest {

    @Test
    void validateProperty() {
        Set<ConstraintViolation<Student>> name = ValidateUtil.validateProperty(new Student(), "name");
        assertFalse(name.isEmpty());
    }

    public static class Student {
        @NotEmpty
        String name;
    }
}
