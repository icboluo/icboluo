package com.icboluo.spring;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
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
public class WebLogAspect {

    /**
     * 统计时间
     */
    ThreadLocal<Long> startTime = new ThreadLocal<>();

    /**
     * 多个切面想用公共的 advice增强方法，使用 || ，使用&&错误
     */
    @Pointcut("webLog1()||webLog2()")
    public void webLog() {
    }

    /**
     * 对于切面可以精确到方法级别，小括号内的小数点作为方法的参数，如果advice方法不需要方法参数，可以使用
     * 2个小数点替代，比较简单
     */
    @Pointcut("execution(public * com.icboluo.controller.WebInterceptorController.getUserCode(..))")
    public void webLog1() {
    }

    @Pointcut("execution(public * com.icboluo.controller.WebInterceptorController.userCodeAnnotation(..))")
    public void webLog2() {
    }

    /**
     * 也可以精确到包级别
     */
    @Pointcut("execution(public * com.icboluo.controller..*(..))")
    public void webLog3() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        startTime.set(System.currentTimeMillis());
    }
}
