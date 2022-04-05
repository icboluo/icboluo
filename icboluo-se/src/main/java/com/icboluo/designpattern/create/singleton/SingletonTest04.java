package com.icboluo.designpattern.create.singleton;

/**
 * 懒汉式
 * 线程安全，效率低，不推荐使用
 *
 * @author icboluo
 * @since 2020/10/17 18:20
 */
public class SingletonTest04 {
    private static SingletonTest04 instance;

    private SingletonTest04() {
    }

    /**
     * 加入同步方法，解决线程不安全的问题
     * 每一次调这个方法进行同步，效率低
     *
     * @return
     */
    public static synchronized SingletonTest04 getInstance() {
        if (instance == null) {
//         第5种写法   不准在这里加同步代码块，进入if条件，线程安全也不能保证
            instance = new SingletonTest04();
        }
        return instance;
    }
}
