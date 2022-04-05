package com.icboluo.designpattern.principle.liskov4.improve;

/**
 * @author icboluo
 * @since 2020-09-02 16:59
 */
 class B extends Base {
//     如果b要用a的方法，采用组合的关系
    private A a = new A();
    public int fun1(int a, int b) {
        return a + b;
    }
    public int fun2(int a, int b) {
        return a * b;
    }

    public int fun3(int a, int b) {
        return this.a.fun1(a, b);
    }
}
