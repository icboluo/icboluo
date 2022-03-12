package com.icboluo.common.redis;

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

    @Pointcut("execution(public * com.icboluo.common.redis.*.*(..))&&!execution(public * com.icboluo.common.redis.RedisConfig.*(..))" +
            "&&!execution(public * com.icboluo.common.redis.AbstractRedis.rCap(..))&&!execution(public * com.icboluo.common.redis.AbstractRedis.rCapOnce(..))")
    public void redisLog() {
        // Only one redis operation pointcut
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

    private String toLogString(Object ret) {
        if (ret == null) {
            return null;
        }
        if (ret instanceof Optional<?> opt) {
            if (opt.isPresent()) {
                ret = opt.get();
            } else {
                return null;
            }
        }
        if (ret.getClass().isArray()) {
            ret = ((Object[]) ret).length;
        }
        if (ret instanceof Collection<?> addressColl) {
            ret = addressColl.size();
        }
        if (ret instanceof Map<?, ?> addressMap) {
            ret = addressMap.size();
        }
        return ret.toString();
    }

    private String toString(Object args) {
        if (args == null) {
            return null;
        }
        if (args.getClass().isArray()) {
            args = Arrays.toString((Object[]) args);
        }
        String complete = args.toString();
        if (complete == null || complete.length() < 100) {
            return complete;
        }
        return complete.substring(0, 50) + "..." + complete.substring(complete.length() - 50);
    }

    private String getClassName(String declaringTypeName) {
        int lastIndexOf = declaringTypeName.lastIndexOf(".");
        return declaringTypeName.substring(lastIndexOf + 1);
    }
}
