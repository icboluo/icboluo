package com.icboluo.configuration;

import com.icboluo.annotation.UserCode;
import com.icboluo.constant.HttpConstant;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @author icboluo
 * @since 2020/10/22 19:25
 */
public class UserCodeResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(UserCode.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest hsr = webRequest.getNativeRequest(HttpServletRequest.class);
        String userCode = hsr.getHeader(HttpConstant.USER_CODE);
        return userCode;
    }
}
