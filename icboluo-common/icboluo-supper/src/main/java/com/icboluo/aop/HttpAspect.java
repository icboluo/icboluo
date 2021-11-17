package com.icboluo.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PropertyFilter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

/**
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
    public Object around(ProceedingJoinPoint joinPoint) {
        Object ans = null;
        try {
            long start = System.currentTimeMillis();
            ans = joinPoint.proceed();
            long end = System.currentTimeMillis();
            log.info("Time [{}] Response {}\n {}", end - start, toSimpleStr(ans, 200),
                    toSimpleStr("no param", 1000));
        } catch (Throwable e) {
            log.error("");
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
        return str.substring(0, length / 2) + str.substring(str.length() - length);
    }
}
