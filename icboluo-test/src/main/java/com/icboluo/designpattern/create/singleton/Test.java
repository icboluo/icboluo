package com.icboluo.designpattern.create.singleton;

/**
 * @author icboluo
 * @date 2020/10/17 18:09
 */
public class Test {
    public static void main(String[] args) {
        SingletonTest01 instance1 = SingletonTest01.getInstance();
        SingletonTest01 instance2 = SingletonTest01.getInstance();
        System.out.println(instance1 == instance2);
        System.out.println(instance1.hashCode());
        System.out.println(instance2.hashCode());
//        饿汉式的使用
        Runtime runtime = Runtime.getRuntime();
    }
}
