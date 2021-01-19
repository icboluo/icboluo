package com.icboluo.annotation.demo_check;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

/**
 * 简单的测试框架
 *
 * @author icboluo
 * @date 2020-08-29 22:28
 */
@Slf4j
class TestCheck09 {
    public static void main(String[] args) {
        Calculator cal = new Calculator();
        Class<? extends Calculator> clazz = cal.getClass();
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Check10.class)) {
                try {
                    method.invoke(cal);
                } catch (Exception e) {
                    e.printStackTrace();
                    log.error(method.getName());
                    log.error("异常名称" + e.getCause().getClass().getSimpleName());
                    log.error("异常原因" + e.getCause().getMessage());
                }
            }
        }
    }
}
