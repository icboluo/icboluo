package com.icboluo.designpattern.behavior.strategy;

import lombok.Setter;

/**
 * @author icboluo
 * @date 2020/11/22 18:36
 */
public abstract class Duck {
    @Setter
    FlyBehavior flyBehavior;
    @Setter
    QuackBehavior quackBehavior;

    public abstract void display();

    public void quack() {
        System.out.println("鸭子叫");
    }

    public void swim() {
        System.out.println("鸭子游泳");
    }

    public void fly() {
//        不等于空，说明鸭子的子类已经付给鸭子类了
        if (flyBehavior != null) {
            flyBehavior.fly();
        }
    }
}
