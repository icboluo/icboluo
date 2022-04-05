package com.icboluo.designpattern.create.factory.simplefactory;

/**
 * @author icboluo
 * @since 2020/10/21 19:55
 */
public class BPizza extends Pizza {
    @Override
    public void prepare() {
        System.out.println(" b pizza 准备好了");
    }
}
