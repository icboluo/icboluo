package com.icboluo.designpattern.structure.facade;

/**
 * @author icboluo
 * @since 2020/12/26 16:05
 */
public class DVDPlayer {

    private static DVDPlayer instance = new DVDPlayer();

    private DVDPlayer() {
    }

    public static DVDPlayer getInstance() {
        return instance;
    }

    public void on() {
        System.out.println("DVD 打开 了");
    }

    public void off() {
        System.out.println("DVD 关闭 了");
    }

    public void play() {
        System.out.println("DVD 开始播放 了");
    }

    public void pause() {
        System.out.println("DVD 暂停 了");
    }
}
