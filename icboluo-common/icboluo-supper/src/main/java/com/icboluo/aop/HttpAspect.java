package com.icboluo.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PropertyFilter;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Map;

/**
 * 项目日志 切面
 *
 * @author icboluo
 * @date 2021-11-17 22:41
 */
@Aspect
@Component
@Slf4j
public class HttpAspect {

    @Pointcut("execution(public * com.icboluo.controller..*.*(..))")
    public void httpPointcut() {
// only one Pointcut
    }

    @Around("httpPointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            log.error("why have a interface not convert attributes,please survey");
            return joinPoint.proceed();
        }
//        url是带http前缀的请求
        HttpServletRequest request = attributes.getRequest();
        long start = System.currentTimeMillis();
        Object ans = joinPoint.proceed();
        long end = System.currentTimeMillis();
//        com.icboluo.controller.ProvinceController.selectAll
//        log.debug("请求类方法CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("Ip {}, Time {}ms Response {}\n {} {}\n{}", getIp(request), end - start, toSimpleStr(ans, 200),
                request.getMethod(), request.getRequestURI(),
                toSimpleStr(Arrays.toString(joinPoint.getArgs()), 1000));

        Map<String, String[]> paramMap = request.getParameterMap();
        if (!ObjectUtils.isEmpty(paramMap)) {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, String[]> entry : paramMap.entrySet()) {
                String[] value = entry.getValue();
                //添加判断
                if (ObjectUtils.isEmpty(value)) {
                    continue;
                }
                for (String paramStr : value) {
                    if (StringUtils.isNotBlank(paramStr)) {
                        sb.append("参数").append(entry.getKey()).append("=");
                        sb.append(paramStr);
                        sb.append(";");
                    }
                }
            }
            log.debug("请求参数PARAM : " + sb);
        }
        return ans;
    }


    private Object toSimpleStr(Object param, int length) {
//        如果值为空值,则排除
        PropertyFilter propertyFilter = (object, name, value) -> !ObjectUtils.isEmpty(value);
        String str = JSON.toJSONString(param, propertyFilter);
        if (str.length() != 0 && str.length() < length) {
            return str;
        }
        return str.substring(0, length / 2) + "..." + str.substring(str.length() - length);
    }

    private static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
