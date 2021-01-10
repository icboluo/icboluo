package com.icboluo.redis;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 项目日志 切面
 *
 * @author icboluo
 */
@Aspect
@Component
@Slf4j
public class RedisLogAspect {

    @Pointcut("execution(public * com.icboluo.redis.*.*(..))")
    public void redisLog() {
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
        log.debug("method : {}.{}({})", getClassName(declaringTypeName), methodName, argsToString(args));
    }

    @AfterReturning(returning = "ret", pointcut = "redisLog()")
    public void doAfterReturning(Object ret) {
        log.debug("return : {}", ret);
    }

    private String argsToString(Object[] args) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < args.length; i++) {
            if (i != args.length - 1) {
                sb.append(args[i]).append(", ");
            } else {
                sb.append(args[i]);
            }
        }
        return sb.toString();
    }

    private String getClassName(String declaringTypeName) {
        int lastIndexOf = declaringTypeName.lastIndexOf(".");
        return declaringTypeName.substring(lastIndexOf + 1);
    }
}