package com.icboluo.designpattern.a4_behavior.template;

/**
 * @author icboluo
 * @since 2020/11/15 20:52
 */
public class Client {
    public static void main(String[] args) {
        SoyaMilk redBeanSoyaMilk = new RedBeanSoyaMilk();
        redBeanSoyaMilk.make();
        SoyaMilk peanutSoyaMilk = new PeanutSoyaMilk();
        peanutSoyaMilk.make();
    }
}
