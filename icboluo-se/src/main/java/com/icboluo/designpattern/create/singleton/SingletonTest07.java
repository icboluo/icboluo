package com.icboluo.designpattern.create.singleton;

/**
 * 静态内部类 推荐使用
 *
 * @author icboluo
 * @since 2020/10/17 18:20
 */
public class SingletonTest07 {
    private static SingletonTest07 instance;

    /**
     * 外部类装载，静态内部类不会执行（实现懒加载）
     */
    private static class SingletonInstance {
        //        jvm在类装载的时候是线程安全的
        private static final SingletonTest07 instance = new SingletonTest07();
    }

    /**
     * @return
     */
    public static SingletonTest07 getInstance() {
        return SingletonInstance.instance;
    }
}
