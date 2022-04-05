package com.icboluo.designpattern.create.factory.abstractfactory.pizza;

/**
 * @author icboluo
 * @since 2020/12/5 18:20
 */
public class LDPepperPizza extends Pizza {

    @Override
    public void prepare() {
        setName("伦敦的胡椒披萨");
        System.out.println("伦敦的胡椒披萨 准备原材料");
    }
}
