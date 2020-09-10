package com.icboluo.designpattern.liskov4.improve;

/**
 * @author icboluo
 * @date 2020-09-02 16:58
 */
 class Liskov {
    public static void main(String[] args) {
        B b = new B();
        int i = b.fun1(1, 2);
        System.out.println("i = " + i);


    }
}
