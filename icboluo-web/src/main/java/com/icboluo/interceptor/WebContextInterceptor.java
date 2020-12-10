package com.icboluo.interceptor;

import com.icboluo.HttpConstant;
import com.icboluo.annotation.WebContextAnno;
import com.icboluo.enumeration.WebContextEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author icboluo
 */
@Slf4j
public class WebContextInterceptor extends HandlerInterceptorAdapter {


    private final List<String> exclude = new ArrayList<>();

    //TODO    使用匿名内部类 + 实例化代码块儿 = 使用两个大括号进行初始化
    {
        exclude.add("aController.init");
        exclude.add("bController.init");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            log.error("handler 转换失败{}", handler);
            return super.preHandle(request, response, handler);
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        WebContextAnno webContextAnno = handlerMethod.getBeanType().getAnnotation(WebContextAnno.class);

        if (webContextAnno != null) {
            if (WebContextEnum.WEB.equals(webContextAnno.service())) {
                String userCode = request.getHeader(HttpConstant.USER_CODE);
                UserContext.set(userCode);
            }
        }
        return super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserContext.remove();
        super.afterCompletion(request, response, handler, ex);
    }
}

