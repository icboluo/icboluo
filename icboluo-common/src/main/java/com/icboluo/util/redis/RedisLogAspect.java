package com.icboluo.util.redis;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 项目日志 切面
 *
 * @author icboluo
 */
@Aspect
@Component
@Slf4j
public class RedisLogAspect {

    @Pointcut("execution(public * com.icboluo.util.redis.*.*(..))")
    public void redisLog() {
        // TODO document why this method is empty
    }


    @Before("redisLog()")
    public void doBefore(JoinPoint joinPoint) {
        if (!log.isDebugEnabled()) {
            return;
        }
        Object[] args = joinPoint.getArgs();
        Signature signature = joinPoint.getSignature();
        String methodName = signature.getName();
        String declaringTypeName = signature.getDeclaringTypeName();
//        如果日志里面用函数，不管日志级别是什么，后面的占位符中的函数都会执行
//        启动模式和日志级别毫无关系
        log.debug("==>  Method: {}.{}({})", getClassName(declaringTypeName), methodName, argsToString(args));
    }

    @AfterReturning(returning = "ret", pointcut = "redisLog()")
    public void doAfterReturning(Object ret) {
        if (!log.isDebugEnabled()) {
            return;
        }
        log.debug("<==  Return: {}", toLogString(ret));
    }

    private String argsToString(Object[] args) {
        if (args == null || args.length == 0) {
            return null;
        }
        return Arrays.stream(args)
                .map(this::toString)
                .collect(Collectors.joining(", "));
    }

    private String toLogString(Object address) {
        if (address == null) {
            return null;
        }
        if (address instanceof Optional<?> opt) {
            if (opt.isPresent()) {
                address = opt.get();
            } else {
                return null;
            }
        }
        if (address.getClass().isArray()) {
            address = ((Object[]) address).length;
        }
        if (address instanceof Collection<?> addressColl) {
            address = addressColl.size();
        }
        if (address instanceof Map<?, ?> addressMap) {
            address = addressMap.size();
        }
        return address.toString();
    }

    private String toString(Object address) {
        if (address == null) {
            return null;
        }
        if (address.getClass().isArray()) {
            address = Arrays.toString((Object[]) address);
        }
        return address.toString();
    }

    private String getClassName(String declaringTypeName) {
        int lastIndexOf = declaringTypeName.lastIndexOf(".");
        return declaringTypeName.substring(lastIndexOf + 1);
    }
}
