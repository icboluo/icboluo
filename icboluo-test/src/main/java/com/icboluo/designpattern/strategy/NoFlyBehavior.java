package com.icboluo.designpattern.strategy;

/**
 * @author icboluo
 * @date 2020/11/22 18:35
 */
public class NoFlyBehavior implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("不能飞");
    }
}
