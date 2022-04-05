package com.icboluo.designpattern.create.singleton;

/**
 * 饿汉式：静态变量实现
 * 没有实现懒加载，类一装载就会实例化，如果没有用到这个实例，就会造成内存浪费
 *
 * @author icboluo
 * @since 2020/10/17 18:07
 */
public class SingletonTest01 {

    /**
     * 构造方法私有化，外部不要访问
     */
    private SingletonTest01() {
    }

    private final static SingletonTest01 instance = new SingletonTest01();

    public static SingletonTest01 getInstance() {
        return instance;
    }
}
