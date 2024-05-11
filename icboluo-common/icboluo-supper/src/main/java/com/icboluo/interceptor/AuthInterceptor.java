package com.icboluo.interceptor;

import com.alibaba.fastjson2.JSON;
import com.icboluo.annotation.AuthAnno;
import com.icboluo.annotation.WebContextAnno;
import com.icboluo.enumerate.ReEnum;
import com.icboluo.util.response.R;
import com.icboluo.util.response.Response;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * @author icboluo
 */
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod handlerMethod)) {
            log.error("handler 转换失败{}", handler);
            return HandlerInterceptor.super.preHandle(request, response, handler);
        }
        String role = request.getHeader("role");
        Method method = handlerMethod.getMethod();
        WebContextAnno webContextAnno = handlerMethod.getBeanType().getAnnotation(WebContextAnno.class);
        AuthAnno auth = method.getAnnotation(AuthAnno.class);
        if (auth == null) {
            return true;
        }
        if (!Objects.equals(role, auth.role())) {
            // 拦截器的异常会抛到前台,请将error写入响应
            ServletOutputStream outputStream = response.getOutputStream();
            Response error = R.error(ReEnum.ROLE_ERROR);
            String str = JSON.toJSONString(error);
            outputStream.write(str.getBytes(StandardCharsets.UTF_8));
            response.setContentType("application/json;charset=UTF-8");
            return false;
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}

