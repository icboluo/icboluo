package com.icboluo.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Locale;

/**
 * @author icboluo
 * @since 2022-06-06 21:24
 */
public class I18Interceptor implements HandlerInterceptor {
    static {
        LocaleContextHolder.setDefaultLocale(Locale.ENGLISH);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String language = request.getHeader("language");
        if (Locale.SIMPLIFIED_CHINESE.getDisplayLanguage().equals(language)) {
            LocaleContextHolder.setLocale(Locale.SIMPLIFIED_CHINESE);
        } else if (Locale.ENGLISH.getDisplayLanguage().equals(language)) {
            LocaleContextHolder.setLocale(Locale.ENGLISH);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        LocaleContextHolder.resetLocaleContext();
    }
}
