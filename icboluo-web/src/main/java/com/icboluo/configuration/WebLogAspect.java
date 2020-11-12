package com.icboluo.configuration;

import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Map;

/**
 * 项目日志 切面
 *
 * @author zengdq
 * 2019年1月5日
 */
@Aspect
@Component
@Slf4j
public class WebLogAspect {


    private static final String PLATFORM_NAME = "xxx项目名称";
    /**
     * 统计时间
     */
    ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut("execution(public * com.icboluo.controller..*.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        startTime.set(System.currentTimeMillis());

        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //打印请求参数
        Map<String, String[]> paramMap = request.getParameterMap();
        if (paramMap != null && paramMap.size() > 0) {
            StringBuffer paramSbf = new StringBuffer();
            for (String mapKey : paramMap.keySet()) {
                String[] mapValue = paramMap.get(mapKey);

                //添加判断
                if (mapValue != null && mapValue.length > 0) {
                    for (String paramStr : mapValue) {
                        if (StringUtils.isNotBlank(paramStr)) {
                            paramSbf.append("参数" + mapKey + "=");
                            paramSbf.append(paramStr);
                            paramSbf.append(";");
                        }
                    }

                }//END if

            }//END for

            //打印日志参数
            log.info(PLATFORM_NAME + "-->request请求参数PARAM : " + paramSbf);
        }//END if        
        // 记录下请求内容
        log.info(PLATFORM_NAME + "-->request请求URL : " + request.getRequestURL().toString());
        log.info(PLATFORM_NAME + "-->request请求方法HTTP_METHOD : " + request.getMethod());
        log.info(PLATFORM_NAME + "-->request请求方法IP : " + getIP(request));
        log.info(PLATFORM_NAME + "-->request请求类方法CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info(PLATFORM_NAME + "-->request请求ARGS : " + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        log.info(PLATFORM_NAME + "-->response请求响应结果RESULT: " + ret);
        log.info(PLATFORM_NAME + "-->response请求响应时间= 【" + (System.currentTimeMillis() - startTime.get()) + "】毫秒");
    }

    /**
     * @param request
     * @return
     */
    private static String getIP(HttpServletRequest request) {
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