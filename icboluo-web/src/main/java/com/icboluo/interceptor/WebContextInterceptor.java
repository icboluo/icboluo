package com.icboluo.interceptor;

import com.icboluo.annotation.WebContextAnno;
import com.icboluo.common.constant.HttpConstant;
import com.icboluo.common.enumeration.WebContextEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author icboluo
 */
@Slf4j
public class WebContextInterceptor implements HandlerInterceptor {

    private final List<String> exclude = new ArrayList<>();

    //TODO    使用匿名内部类 + 实例化代码块儿 = 使用两个大括号进行初始化
    {
        exclude.add("aController.init");
        exclude.add("bController.init");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod handlerMethod)) {
            log.error("handler 转换失败{}", handler);
            return HandlerInterceptor.super.preHandle(request, response, handler);
        }
        WebContextAnno webContextAnno = handlerMethod.getBeanType().getAnnotation(WebContextAnno.class);
        webContextAnno = webContextAnno == null ? handlerMethod.getBeanType().getAnnotation(WebContextAnno.class) : webContextAnno;
        if (webContextAnno == null || !WebContextEnum.WEB.equals(webContextAnno.service())) {
            String userCode = request.getHeader(HttpConstant.USER_CODE);
            UserContext.set(userCode);
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserContext.remove();
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}

