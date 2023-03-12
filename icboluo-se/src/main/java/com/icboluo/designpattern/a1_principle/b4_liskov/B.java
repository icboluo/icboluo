package com.icboluo.designpattern.a1_principle.b4_liskov;

/**
 * @author icboluo
 * @since 2020-09-02 16:59
 */
 class B extends A {
    @Override
    public int fun1(int a, int b) {
        return a + b;
    }
    public int fun2(int a, int b) {
        return a * b;
    }
}
