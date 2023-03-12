package com.icboluo.designpattern.a1_principle.b4_liskov.improve;

/**
 * @author icboluo
 * @since 2020-09-02 16:58
 */
 class Liskov {
    public static void main(String[] args) {
        B b = new B();
        int i = b.fun1(1, 2);
        System.out.println("i = " + i);


    }
}
