package com.icboluo.designpattern.behavior.strategy;

/**
 * @author icboluo
 * @since 2020/11/22 18:39
 */
public class WildDuck extends Duck {

    public WildDuck() {
        flyBehavior = new GoodFlyBehavior();
    }

    @Override
    public void display() {
        System.out.println("这是野鸭");
    }
}
