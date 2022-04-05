package com.icboluo.designpattern.create.singleton;

/**
 * 懒汉式 双重检查 推荐使用
 *
 * @author icboluo
 * @since 2020/10/17 18:20
 */
public class SingletonTest06 {
    /**
     * volatile 防止指令重排、如果值被更改，刷新到别的线程？
     */
    private static volatile SingletonTest06 instance;

    private SingletonTest06() {
    }

    /**
     * @return
     */
    public static  SingletonTest06 getInstance() {
        if (instance == null) {
//            即便进入了if条件,下面还有一重检查
            synchronized (SingletonTest06.class) {
                if (instance == null) {
                    instance = new SingletonTest06();
                }
            }
        }
        return instance;
    }
}
