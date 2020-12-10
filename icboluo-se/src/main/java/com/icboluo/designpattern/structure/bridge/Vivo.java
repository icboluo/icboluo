package com.icboluo.designpattern.structure.bridge;

/**
 * @author icboluo
 * @date 2020/12/10 22:32
 */
public class Vivo implements Brand {

    @Override
    public void open() {
        System.out.println("vivo手机开机了");
    }

    @Override
    public void close() {
        System.out.println("vivo手机关机了");

    }

    @Override
    public void call() {
        System.out.println("vivo手机打电话");
    }
}
