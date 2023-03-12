package com.icboluo.designpattern.a4_behavior.template;

/**
 * @author icboluo
 * @since 2020/11/15 20:57
 */
public class RedBeanSoyaMilk extends SoyaMilk {
    @Override
    void addCondiments() {
        System.out.println("加入红豆");
    }
}
