package com.icboluo.interceptor;

import com.icboluo.util.IcBoLuoException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.util.ObjectUtils;

import java.util.Locale;


/**
 * @author icboluo
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class WebContext {
    private static final ThreadLocal<Header> HEADER_CONTEXT = new InheritableThreadLocal<>();

    static {
        // 这个是spring设置的环境，主要用于web层，多线程
        LocaleContextHolder.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
        // 这个是jdk设置的环境，是java原生的工具，会影响所用使用Locale的api，例如 DateFormat NumberFormat MessageFormat
        Locale.setDefault(Locale.SIMPLIFIED_CHINESE);
    }


    @AllArgsConstructor
    private static class Header {
        String userCode;
    }

    public static String user() {
        validate();
        return HEADER_CONTEXT.get().userCode;
    }

    public static boolean isEn() {
        return LocaleContextHolder.getLocale() == Locale.ENGLISH;
    }

    private static void validate() {
        boolean isNull = ObjectUtils.isEmpty(HEADER_CONTEXT.get()) || ObjectUtils.isArray(HEADER_CONTEXT.get().userCode);
        if (isNull) {
            throw new IcBoLuoException("No Context in Header");
        }
    }

    public static void set(String userCode, Locale locale) {
        Header header = new Header(userCode);
        HEADER_CONTEXT.set(header);
        LocaleContextHolder.setLocale(locale);
    }

    public static void remove() {
        HEADER_CONTEXT.remove();
        LocaleContextHolder.resetLocaleContext();
    }
}
