package com.icboluo.designpattern.a4_behavior.strategy;

/**
 * @author icboluo
 * @since 2020/11/22 18:39
 */
public class ToyDuck extends Duck {

    public ToyDuck() {
        flyBehavior = new NoFlyBehavior();
    }

    @Override
    public void display() {
        System.out.println("这是玩具鸭");
    }
}
