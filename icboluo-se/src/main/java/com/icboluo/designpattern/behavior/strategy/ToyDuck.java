package com.icboluo.designpattern.behavior.strategy;

/**
 * @author icboluo
 * @date 2020/11/22 18:39
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
