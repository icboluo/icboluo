package com.icboluo.designpattern.structure.bridge;

/**
 * @author icboluo
 * @date 2020/12/10 22:32
 */
public class XiaoMi implements Brand {

    @Override
    public void open() {
        System.out.println("小米手机开机了");
    }

    @Override
    public void close() {
        System.out.println("小米手机关机了");

    }

    @Override
    public void call() {
        System.out.println("小米手机打电话");
    }
}
