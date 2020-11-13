package com.icboluo.resolver;

import com.icboluo.annotation.RequestBodyParam;
import com.icboluo.util.IOHelper;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.lang.reflect.Parameter;

/**
 * @author icboluo
 * @date 2020/10/22 19:25
 */
public class RequestBodyParamResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(RequestBodyParam.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest hsr = webRequest.getNativeRequest(HttpServletRequest.class);
        BufferedReader reader = hsr.getReader();
        String s = IOHelper.readBufferedReader(reader);
        RequestBodyParam parameterAnnotation = parameter.getParameterAnnotation(RequestBodyParam.class);
        Parameter webParameter = parameter.getParameter();
        String webParameterName = webParameter.getName();

        String name = parameterAnnotation.name();
        return null;
    }
}
