package com.icboluo.spring.accountanno.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component("logger")
@Aspect
public class Logger {
/*    public void printLog() {
        System.out.println("打印log");
    }
    public void printLog2() {
        System.out.println("打印log2");
    }

    public void printLog3() {
        System.out.println("打印log3");
    }*/

    @Pointcut("execution(* com.task.service.impl.*.*(..))")
    public void pt1() {

    }

    //前置通知
    //@Before("pt1()")
    public void beforePrintLog() {
        System.out.println("前置通知执行了");
    }

    //后置通知
    //  @AfterReturning("pt1()")
    public void afterReturningPrintLog() {
        System.out.println("后置通知执行了");
    }

    //异常通知
    //@AfterThrowing("pt1()")
    public void afterThrowingPrintLog() {
        System.out.println("异常通知执行了");
    }

    //最终通知
    // @After("pt1()")
    public void afterPrintLog() {
        System.out.println("最终通知执行了");
    }


    @Around("pt1()")
    public Object around(ProceedingJoinPoint pjp) {
        Object proceed = null;
        try {
            beforePrintLog();
            proceed = pjp.proceed();
            afterReturningPrintLog();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            afterThrowingPrintLog();
        } finally {
            afterPrintLog();
        }
        return proceed;
    }
}
