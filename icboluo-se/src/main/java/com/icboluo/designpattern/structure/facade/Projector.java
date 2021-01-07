package com.icboluo.designpattern.structure.facade;

/**
 * 投影仪
 *
 * @author icboluo
 * @date 2020/12/26 16:05
 */
public class Projector {

    private static Projector instance = new Projector();

    private Projector() {
    }

    public static Projector getInstance() {
        return instance;
    }

    public void on() {
        System.out.println("投影仪 打开 了");
    }

    public void off() {
        System.out.println("投影仪 关闭 了");
    }

    public void focus() {
        System.out.println("投影仪 正在聚焦 了");
    }
}
