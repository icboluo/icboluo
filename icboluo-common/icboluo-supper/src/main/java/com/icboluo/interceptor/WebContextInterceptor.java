package com.icboluo.interceptor;

import com.icboluo.annotation.WebContextAnno;
import com.icboluo.constant.HttpConstant;
import com.icboluo.enumerate.ServiceNameEnum;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

/**
 * @author icboluo
 */
@Slf4j
public class WebContextInterceptor implements HandlerInterceptor {

    private final List<String> exclude = new ArrayList<>();

    {
        exclude.add("aController.init");
        exclude.add("bController.init");
    }

    static {
        LocaleContextHolder.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
    }

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod handlerMethod)) {
            log.error("handler 转换失败{}", handler);
            return HandlerInterceptor.super.preHandle(request, response, handler);
        }
        WebContextAnno webContextAnno = handlerMethod.getBeanType().getAnnotation(WebContextAnno.class);
        boolean present = Optional.ofNullable(webContextAnno)
                .filter(wca -> ServiceNameEnum.WEB == wca.service())
                .isPresent();
        if (present) {
            String userCode = request.getHeader(HttpConstant.USER_CODE);
            UserContext.set(userCode);
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler, Exception ex) throws Exception {
        UserContext.remove();
//        接口名.super.默认方法名 可以直接调用接口中的方法
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}

