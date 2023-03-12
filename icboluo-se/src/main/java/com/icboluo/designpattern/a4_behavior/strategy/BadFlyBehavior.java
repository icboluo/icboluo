package com.icboluo.designpattern.a4_behavior.strategy;

/**
 * @author icboluo
 * @since 2020/11/22 18:35
 */
public class BadFlyBehavior implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("飞行技术菜鸟");
    }
}
