package com.icboluo.designpattern.a4_behavior.strategy;

/**
 * @author icboluo
 * @since 2020/11/22 18:34
 */
public class Client {
    public static void main(String[] args) {
        WildDuck wildDuck = new WildDuck();
        wildDuck.fly();
        ToyDuck toyDuck = new ToyDuck();
        toyDuck.fly();
        BeijingDuck beijingDuck = new BeijingDuck();
        beijingDuck.fly();
        beijingDuck.setFlyBehavior(new NoFlyBehavior());
        beijingDuck.fly();

    }
}
