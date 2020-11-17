package com.icboluo.resolver;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
import java.lang.reflect.Type;

/**
 * @author icboluo
 * @date 2020/10/22 19:25
 */
public class RequestBodyParamResolver implements HandlerMethodArgumentResolver {

    private JSONObject requestBody = null;

    private boolean isGetBodyParam = false;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(RequestBodyParam.class);
    }

    /**
     * 参数解析循环调用，第一次读取到body参数即可，其余次数读的是空
     *
     * @param parameter
     * @param mavContainer
     * @param webRequest
     * @param binderFactory
     * @return
     * @throws Exception
     */
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest hsr = webRequest.getNativeRequest(HttpServletRequest.class);
        BufferedReader reader = hsr.getReader();
        if (!isGetBodyParam) {
            String body = IOHelper.readBufferedReader(reader);
            requestBody = JSON.parseObject(body);
            isGetBodyParam = true;
        }
        RequestBodyParam parameterAnnotation = parameter.getParameterAnnotation(RequestBodyParam.class);
        Parameter webParameter = parameter.getParameter();
        String parameterName = webParameter.getName();
        String name = parameterAnnotation.name();
        Object o = requestBody.get(parameterName);
        Type genericParameterType = parameter.getGenericParameterType();
        Type nestedGenericParameterType = parameter.getNestedGenericParameterType();
        Class<?> nestedParameterType = parameter.getNestedParameterType();
        Class<?> parameterType = parameter.getParameterType();
        return o;
    }
}
