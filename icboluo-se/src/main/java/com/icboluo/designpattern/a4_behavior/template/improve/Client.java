package com.icboluo.designpattern.a4_behavior.template.improve;

/**
 * @author icboluo
 * @since 2020/11/15 21:05
 */
public class Client {
    public static void main(String[] args) {
        SoyaMilk pureSoyaMilk = new PureSoyaMilk();
        pureSoyaMilk.make();
    }
}
