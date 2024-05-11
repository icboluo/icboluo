package com.icboluo.common.converter;

import com.icboluo.util.DateUtil;
import lombok.NonNull;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;

/**
 * 请注意，RequestParam 里面的（URL中的）参数取的是这里的转换器
 *
 * @author icboluo
 * @see com.icboluo.common.serializer.LocalDateDeserializer RequestBody 里面的取的是这里的反序列化工具
 * @since 2022-03-12 22:30
 */
public class StringToLocalDateConverter implements Converter<String, LocalDate> {
    @Override
    public LocalDate convert(@NonNull String str) {
        if (str.contains("Z")) {
            str = str.replace("Z", "");
        }
        return DateUtil.allToDate(str);
    }
}
