package com.icboluo.filterinterceptor;

import com.icboluo.annotation.AuthAnno;
import com.icboluo.enumerate.ExceptionEnum;
import com.icboluo.util.IcBoLuoException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Optional;

/**
 * @author icboluo
 */
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod handlerMethod)) {
            log.error("handler 转换失败{}", handler);
            return HandlerInterceptor.super.preHandle(request, response, handler);
        }
        String role = request.getHeader("role");
        Method method = handlerMethod.getMethod();
        AuthAnno auth = method.getAnnotation(AuthAnno.class);
        if (auth != null) {
//             TODO 可以简化
            boolean present = Optional.of(auth)
                    .filter(au -> au.role().equals(role))
                    .isPresent();
            if (!present) {
//                TODO 拦截器的异常会抛到前台
                throw new IcBoLuoException(ExceptionEnum.ROLE_ERROR);
            }
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}

