package com.icboluo.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PropertyFilter;
import jakarta.servlet.http.HttpServletRequest;
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

    @Pointcut("execution(public * com.icboluo.controller..*.*(..))")
    public void httpPointcut() {
        // only one Pointcut
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
                arg = toSimpleStr(joinPoint.getArgs(), 1000);
            } catch (Exception exception) {
                arg = " Param parse error !!!";
            }
        }
        try {
            // 异常应该交给全局异常来处理，这里不要越俎代庖
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
        long end = System.currentTimeMillis();
        return "Time " + (end - gmtStart);
    }


    private static void customizationLog(HttpServletRequest request) {
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
                    if (StringUtils.hasText(paramStr)) {
                        sb.append("参数").append(entry.getKey()).append("=");
                        sb.append(paramStr);
                        sb.append(";");
                    }
                }
            }
            log.debug("请求参数PARAM : " + sb);
        }
    }


    private String toSimpleStr(Object param, int length) {
        // 如果值为空值,则排除
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
