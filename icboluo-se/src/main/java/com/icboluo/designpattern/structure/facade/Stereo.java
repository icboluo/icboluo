package com.icboluo.designpattern.structure.facade;

/**
 * 立体声
 *
 * @author icboluo
 * @date 2020/12/26 16:05
 */
public class Stereo {

    private static Stereo instance = new Stereo();

    private Stereo() {
    }

    public static Stereo getInstance() {
        return instance;
    }

    public void on() {
        System.out.println("立体声 打开 了");
    }

    public void off() {
        System.out.println("立体声 关闭 了");
    }

    public void up() {
        System.out.println("立体声 调大 了");
    }

    public void down() {
        System.out.println("立体声 调小 了");
    }
}
