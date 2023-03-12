package com.icboluo.designpattern.a4_behavior.strategy;

/**
 * @author icboluo
 * @since 2020/11/22 18:39
 */
public class BeijingDuck extends Duck {

    public BeijingDuck() {
        flyBehavior = new BadFlyBehavior();
    }

    @Override
    public void display() {
        System.out.println("这是北京鸭");
    }
}
