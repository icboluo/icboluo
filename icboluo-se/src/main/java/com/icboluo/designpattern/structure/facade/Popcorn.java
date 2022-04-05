package com.icboluo.designpattern.structure.facade;

/**
 * 爆米花机
 *
 * @author icboluo
 * @since 2020/12/26 16:08
 */
public class Popcorn {
    private static Popcorn instance = new Popcorn();

    private Popcorn() {
    }

    public static Popcorn getInstance() {
        return instance;
    }

    public void on() {
        System.out.println("爆米花机 打开 了");
    }

    public void off() {
        System.out.println("爆米花机 关闭 了");
    }

    public void pop() {
        System.out.println("爆米花机 正在出爆米花 了");
    }
}
