package com.icboluo.common.converter;

import com.icboluo.util.DateUtil;
import lombok.NonNull;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;

/**
 * @author icboluo
 * @since 2022-03-12 22:30
 */
public class StringToLocalDateTimeConverter implements Converter<String, LocalDateTime> {
    @Override
    public LocalDateTime convert(@NonNull String str) {
        return DateUtil.allToDateTime(str);
    }
}
