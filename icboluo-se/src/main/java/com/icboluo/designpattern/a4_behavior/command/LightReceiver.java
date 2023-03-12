package com.icboluo.designpattern.a4_behavior.command;

/**
 * @author icboluo
 * @since 2020/11/15 21:28
 */
public class LightReceiver {

    public void on() {
        System.out.println("点灯打开了..");
    }

    public void off() {
        System.out.println("点灯关闭了..");
    }
}
