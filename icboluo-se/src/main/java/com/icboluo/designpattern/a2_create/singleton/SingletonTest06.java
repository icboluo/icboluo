package com.icboluo.designpattern.a2_create.singleton;

/**
 * 懒汉式 双重检查 推荐使用
 *
 * @author icboluo
 * @since 2020/10/17 18:20
 */
public class SingletonTest06 {
    /**
     * 易挥发的，标记说明每次使用这个变量都到主内存中进行读取（防止指令重排；保证变量的可见性
     * <p>防止指令重排（没有指令重排的功能感觉也是安全的，只会有一个线程进入赋值语句...
     * <p>如果值被更改，刷新到别的线程
     */
    private static volatile SingletonTest06 instance;

    private SingletonTest06() {
    }

    /**
     * @return 单例
     */
    public static SingletonTest06 getInstance() {
        if (instance != null) {
            return instance;
        }
        // sync 可以修饰静态内容和成员内容
        // 即便进入了if条件,下面还有一重检查
        synchronized (SingletonTest06.class) {
            if (instance == null) {
                /**
                 * new 操作有3个指令：
                 * 1.堆空间分配内存
                 * 2.初始化
                 * 3.栈中变量执行堆内存元素
                 */
                instance = new SingletonTest06();
            }
        }
        return instance;
    }
}

