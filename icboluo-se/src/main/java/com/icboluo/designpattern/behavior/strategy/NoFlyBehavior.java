package com.icboluo.designpattern.behavior.strategy;

/**
 * @author icboluo
 * @since 2020/11/22 18:35
 */
public class NoFlyBehavior implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("不能飞");
    }
}
