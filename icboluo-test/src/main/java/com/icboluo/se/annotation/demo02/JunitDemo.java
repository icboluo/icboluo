package com.icboluo.se.annotation.demo02;

/**
 * @author icboluo
 * @date 2020-08-12 16:57
 */
public class JunitDemo {
    @MyTest
    public void method() {
        System.out.println("method");
    }

    @MyTest
    public void method1() {
        System.out.println("method1");
    }

    @MyTest
    public void method2() {
        System.out.println("method2");
    }
}
