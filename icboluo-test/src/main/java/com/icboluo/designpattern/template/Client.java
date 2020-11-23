package com.icboluo.designpattern.template;

/**
 * @author icboluo
 * @date 2020/11/15 20:52
 */
public class Client {
    public static void main(String[] args) {
        SoyaMilk redBeanSoyaMilk = new RedBeanSoyaMilk();
        redBeanSoyaMilk.make();
        SoyaMilk peanutSoyaMilk = new PeanutSoyaMilk();
        peanutSoyaMilk.make();
    }
}
