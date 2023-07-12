package com.icboluo.designpattern.a2_create.singleton;

/**
 * 懒汉式 双重检查 推荐使用
 *
 * @author icboluo
 * @since 2020/10/17 18:20
 */
public class SingletonTest06_ok {
    /**
     * 主内存：（共享内存，本地内存（比如程序的计数器（每一个线程都有一个私有的本地内存来存储共享变量的副本
     * 易挥发的，标记说明每次使用这个变量都到主内存中进行读取
     * <p>volatile 的2个作用：防止指令重排；保证变量的可见性
     * <p>保证变量的可见性：如果值被更改，刷新到别的线程
     * <p>防止指令重排（没有指令重排的功能感觉也是安全的，只会有一个线程进入赋值语句...
     * <p>volatile 防护的是返回值的问题
     */
    private static volatile SingletonTest06_ok instance;

    private SingletonTest06_ok() {
    }

    /**
     * @return 单例
     */
    public static SingletonTest06_ok getInstance() {
        if (instance != null) {
            return instance;
        }
        // sync 可以修饰静态内容和成员内容
        // 即便进入了if条件,下面还有一重检查
        // sync修饰的方法没有使用monitor enter指令，使用的是acc...标志
        // volatile 关键字能保证数据的可见性，但不能保证数据的原子性。synchronized 关键字两者都能保证
        synchronized (SingletonTest06_ok.class) {
            // 这个null的判断是为了判断对象是否创建
            if (instance == null) {
                /**
                 * new 操作有3个指令：
                 * 1.堆空间分配内存
                 * 2.初始化
                 * 3.栈中变量执行堆内存元素
                 */
                instance = new SingletonTest06_ok();
            }
        }
        return instance;
    }
}

