package com.icboluo.designpattern.behavior.strategy;

/**
 * @author icboluo
 * @since 2020/11/22 18:35
 */
public class GoodFlyBehavior implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("飞行技术高超");
    }
}
