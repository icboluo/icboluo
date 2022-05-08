package com.icboluo.common;

import com.icboluo.annotation.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author icboluo
 * @see ResponseResultHandler 拦截之后的处理逻辑
 * @since 2021-05-13 23:05
 */
@Slf4j
public class ResponseResultInterceptor implements HandlerInterceptor {

    /**
     * 标记Res标准化的key
     */
    protected static String resAnno = "resAnno";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod handlerMethod) {
            Class<?> cla = handlerMethod.getBeanType();
            Method method = handlerMethod.getMethod();
            if (cla.isAnnotationPresent(ResponseResult.class)) {
                request.setAttribute(resAnno, cla.getAnnotation(ResponseResult.class));
            } else if (method.isAnnotationPresent(ResponseResult.class)) {
                request.setAttribute(resAnno, method.getAnnotation(ResponseResult.class));
            }
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
