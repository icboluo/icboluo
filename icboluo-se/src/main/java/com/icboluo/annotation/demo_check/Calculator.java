package com.icboluo.annotation.demo_check;

/**
 * @author icboluo
 * @date 2020-08-29 22:33
 */
public class Calculator {
    @Check10
    public void a() {
        System.out.println(1 + 0);
    }

    @Check10
    public void b() {
        System.out.println(1 - 0);
    }

    @Check10
    public void c() {
        System.out.println(1 * 0);
    }

    @Check10
    public void d() {
        System.out.println(1 / 0);
    }

    public void e() {
        System.out.println(1 + 1);
    }
}
