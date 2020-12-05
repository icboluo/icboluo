package com.icboluo.designpattern.create.factory.abstractfactory.pizza;

/**
 * @author icboluo
 * @date 2020/12/5 18:20
 */
public class LDCheesePizza extends Pizza {

    @Override
    public void prepare() {
        setName("伦敦的奶酪披萨");
        System.out.println("伦敦的奶酪披萨 准备原材料");
    }
}
