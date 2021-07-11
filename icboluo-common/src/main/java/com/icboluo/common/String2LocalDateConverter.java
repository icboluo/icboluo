package com.icboluo.common;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class String2LocalDateConverter implements Converter<String, LocalDate> {
    @Override
    public LocalDate convert(String str) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(str, fmt);
    }
}
