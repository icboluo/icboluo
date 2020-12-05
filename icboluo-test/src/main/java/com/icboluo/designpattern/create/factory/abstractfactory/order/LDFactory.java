package com.icboluo.designpattern.create.factory.abstractfactory.order;

import com.icboluo.designpattern.create.factory.abstractfactory.pizza.LDCheesePizza;
import com.icboluo.designpattern.create.factory.abstractfactory.pizza.LDPepperPizza;
import com.icboluo.designpattern.create.factory.abstractfactory.pizza.Pizza;

/**
 * @author icboluo
 * @date 2020/12/5 18:53
 */
public class LDFactory implements AbsFactory{

    @Override
    public Pizza creatPizza(String orderType) {
        Pizza pizza = null;
        if ("cheese".equals(orderType)) {
            pizza = new LDCheesePizza();
        } else if ("pepper".equals(orderType)) {
            pizza = new LDPepperPizza();
        }
        return pizza;
    }
}
