package com.icboluo.designpattern.singleton;

/**
 * 懒汉式 线程不安全
 * 开发中禁止使用这种方式,有潜在的风险
 *
 * @author icboluo
 * @date 2020/10/17 18:20
 */
public class SingletonTest03 {
    private static SingletonTest03 instance;

    private SingletonTest03() {
    }

    /**
     * 用到这个实例的时候才创建对象
     *
     * @return
     */
    public static SingletonTest03 getInstance() {
//        如果一个线程执行到if内部,未来得及执行代码块中的内容,另一个线程就过来了,会创建多个实例,所以
//        多线程环境下不可使用这种方式
        if (instance == null) {
            instance = new SingletonTest03();
        }
        return instance;
    }
}
