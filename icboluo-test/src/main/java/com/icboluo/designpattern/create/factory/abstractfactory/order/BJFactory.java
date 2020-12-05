package com.icboluo.designpattern.create.factory.abstractfactory.order;

import com.icboluo.designpattern.create.factory.abstractfactory.pizza.BJCheesePizza;
import com.icboluo.designpattern.create.factory.abstractfactory.pizza.BJPepperPizza;
import com.icboluo.designpattern.create.factory.abstractfactory.pizza.Pizza;

/**
 * @author icboluo
 * @date 2020/12/5 18:53
 */
public class BJFactory implements AbsFactory{

    @Override
    public Pizza creatPizza(String orderType) {
        Pizza pizza = null;
        if ("cheese".equals(orderType)) {
            pizza = new BJCheesePizza();
        } else if ("pepper".equals(orderType)) {
            pizza = new BJPepperPizza();
        }
        return pizza;
    }
}
