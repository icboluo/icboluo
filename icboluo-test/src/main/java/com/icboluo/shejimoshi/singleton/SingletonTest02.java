package com.icboluo.shejimoshi.singleton;

/**
 * 饿汉式 静态代码块实现
 *
 * @author icboluo
 * @date 2020/10/17 18:15
 */
public class SingletonTest02 {

    private SingletonTest02() {
    }

    private static SingletonTest02 instance;

    /**
     * 在静态代码块中创建单例对象
     */
    static {
        instance = new SingletonTest02();
    }

    public static SingletonTest02 getInstance() {
        return instance;
    }
}
