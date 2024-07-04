package com.icboluo.aop;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.filter.PropertyFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;
import java.util.Map;


/**
 * 项目日志 切面
 *
 * @author icboluo
 * @since 2021-11-17 22:41
 */
@Aspect
@Component
@Slf4j
public class HttpAspect {

    private static final String UN_KNOWN = "unKnown";

    @Pointcut("execution(public * com.icboluo.controller..*.*(..))")
    public void httpPointcut() {
        // only one Pointcut, no method body
    }

    @Around("httpPointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        long gmtStart = System.currentTimeMillis();
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            // 对于直接调用web层的接口，是无法转换成request的，这种情况，请不要直接调用web层
            log.error("why have a interface not convert attributes,please survey");
            return joinPoint.proceed();
        }
        // com.icboluo.controller.ProvinceController.selectAll
        // log.debug(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        // url是带http前缀的请求
        HttpServletRequest request = attributes.getRequest();
        String arg = null;
        if (log.isInfoEnabled()) {
            try {
                arg = toJsonString(joinPoint.getArgs());
            } catch (Exception exception) {
                arg = " Param parse error !!!";
            }
        }
        try {
            // 此块本不应该捕获异常，异常有专门的全局异常来处理，这里不要越俎代庖
            // 但是部分情况不处理，会造成切面抛出其他的异常（吃掉原异常，UndeclaredThrowableException）,这是不希望看到的（异常应该由异常产生的地方抛出）
            // 所以此处捕获最大地异常，并将异常直接返回，避免异常类型被替换，造成难以定位的现象
            Object ans = joinPoint.proceed();
            if (log.isInfoEnabled()) {
                log.info(getMsg(gmtStart, request, arg));
            }
            return ans;
        } catch (Throwable e) {
            log.error(getMsg(gmtStart, request, arg));
            throw e;
        }
    }

    private String getMsg(long gmtStart, HttpServletRequest request, String arg) {
        String methodUrl = null;
        String time = "Time: " + (System.currentTimeMillis() - gmtStart);
        try {
            methodUrl = methodUrl(request);
            String userIp = "userId" + " : " + "pid" + " : " + getIp(request);
            return time + ", [" + userIp + "] " + methodUrl + arg;
        } catch (Exception ex) {
            log.error("log record message error, url is " + methodUrl, ex);
            return "";
        }
    }

    /**
     * 获取请求的 方法+URL字符串
     *
     * @param request http请求
     * @return eg: POST: /one/two
     */
    private String methodUrl(HttpServletRequest request) {
        String method = request.getMethod();
        String requestURI = request.getRequestURI();
        if (StringUtils.hasText(requestURI)) {
            String url = requestURI.startsWith("/serviceName") ? requestURI.substring("/serviceName".length()) : requestURI;
            return method + " " + url;
        }
        return method + " " + requestURI;
    }

    private String toJsonString(Object[] args) {
        if (ObjectUtils.isEmpty(args)) {
            return "";
        }
        // 此处的切面信息取的是服务端映射到的参数，并非接口中传输的参数；过滤掉参数中的响应
        args = Arrays.stream(args)
                .filter(arg -> !(arg instanceof HttpServletRequest) && !(arg instanceof HttpServletResponse))
                .toArray();
        if (ObjectUtils.isEmpty(args)) {
            return "";
        }
        String ans = ", ";
        if (args.length == 1) {
            ans += JSON.toJSONString(args[0]);
        } else {
            ans += JSON.toJSONString(args);
            if (ans.length() > 1000) {
                ans = ans.substring(0, 495) + "... ..." + ans.substring(ans.length() - 495);
            }
        }
        return ans;
    }

    private static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");

        if (ip == null || ip.isEmpty() || UN_KNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || UN_KNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || UN_KNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
