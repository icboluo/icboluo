package com.icboluo.designpattern.create.factory.factorymethod.pizza;

/**
 * @author icboluo
 * @date 2020/12/5 18:20
 */
public class BJCheesePizza extends Pizza {

    @Override
    public void prepare() {
        setName("北京的奶酪披萨");
        System.out.println("北京的奶酪披萨 准备原材料");
    }
}
