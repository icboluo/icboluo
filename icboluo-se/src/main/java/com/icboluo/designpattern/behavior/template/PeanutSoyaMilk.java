package com.icboluo.designpattern.behavior.template;

/**
 * @author icboluo
 * @since 2020/11/15 20:57
 */
public class PeanutSoyaMilk extends SoyaMilk {
    @Override
    void addCondiments() {
        System.out.println("加入花生");
    }
}
