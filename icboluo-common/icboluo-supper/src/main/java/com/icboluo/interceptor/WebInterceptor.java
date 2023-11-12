package com.icboluo.interceptor;

import com.icboluo.constant.HttpConstant;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Locale;

/**
 * 拦截器是不需要加入到bean容器中的，加了也没用
 * 拦截器需要添加到 WebMvcConfigurer 里面才行，不能实现一个接口空放着，直接加入bean容器中也不行，完全不需要加入bean容器中
 *
 * @author icboluo
 */
@Slf4j
public class WebInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws Exception {
        // user-code
        String userCode = request.getHeader(HttpConstant.USER_CODE);
        // language
        String language = request.getHeader(HttpConstant.LANGUAGE);
        if (Locale.SIMPLIFIED_CHINESE.getDisplayLanguage().equals(language)) {
            LocaleContextHolder.setLocale(Locale.SIMPLIFIED_CHINESE);
            WebContext.set(userCode, Locale.SIMPLIFIED_CHINESE);
        } else if (Locale.ENGLISH.getDisplayLanguage().equals(language)) {
            LocaleContextHolder.setLocale(Locale.ENGLISH);
            WebContext.set(userCode, Locale.ENGLISH);
        } else {
            WebContext.set(userCode, Locale.ENGLISH);
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler, Exception ex) throws Exception {
        WebContext.remove();
        // 接口名.super.默认方法名 可以直接调用接口中的方法
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}

