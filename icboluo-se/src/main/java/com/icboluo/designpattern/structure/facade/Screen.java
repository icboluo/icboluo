package com.icboluo.designpattern.structure.facade;

/**
 * 屏幕
 *
 * @author icboluo
 * @since 2020/12/26 16:05
 */
public class Screen {

    private static Screen instance = new Screen();

    private Screen() {
    }

    public static Screen getInstance() {
        return instance;
    }

    public void up() {
        System.out.println("屏幕 上升 了");
    }

    public void down() {
        System.out.println("屏幕 下降 了");
    }
}
