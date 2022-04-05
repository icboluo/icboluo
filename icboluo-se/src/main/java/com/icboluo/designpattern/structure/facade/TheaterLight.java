package com.icboluo.designpattern.structure.facade;

/**
 * 灯光
 *
 * @author icboluo
 * @since 2020/12/26 16:05
 */
public class TheaterLight {

    private static TheaterLight instance = new TheaterLight();

    private TheaterLight() {
    }

    public static TheaterLight getInstance() {
        return instance;
    }

    public void on() {
        System.out.println("灯光 打开 了");
    }

    public void off() {
        System.out.println("灯光 关闭 了");
    }

    public void bright() {
        System.out.println("灯光 调亮 了");
    }

    public void dim() {
        System.out.println("灯光 调暗 了");
    }
}
