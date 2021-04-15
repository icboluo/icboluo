package com.icboluo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author icboluo
 * @date 2021-05-13 23:05
 */
@Slf4j
@Component
public class ResponseResultInterceptor implements HandlerInterceptor {

    public static final String abc = "abc";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod handlerMethod) {
            Class<?> cla = handlerMethod.getBeanType();
            Method method = handlerMethod.getMethod();
            if (cla.isAnnotationPresent(ResponseResult.class)) {
                request.setAttribute(abc, cla.getAnnotation(ResponseResult.class));
            } else if (method.isAnnotationPresent(ResponseResult.class)) {
                request.setAttribute(abc, method.getAnnotation(ResponseResult.class));
            }
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
