package com.icboluo.designpattern.a2_create.factory.abstractfactory.pizza;

/**
 * @author icboluo
 * @since 2020/12/5 18:20
 */
public class BJPepperPizza extends Pizza {

    @Override
    public void prepare() {
        setName("北京的胡椒披萨");
        System.out.println("北京的胡椒披萨 准备原材料");
    }
}
