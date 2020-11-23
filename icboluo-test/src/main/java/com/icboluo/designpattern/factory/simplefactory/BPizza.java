package com.icboluo.designpattern.factory.simplefactory;

/**
 * @author icboluo
 * @date 2020/10/21 19:55
 */
public class BPizza extends Pizza {
    @Override
    public void prepare() {
        System.out.println(" b pizza 准备好了");
    }
}
