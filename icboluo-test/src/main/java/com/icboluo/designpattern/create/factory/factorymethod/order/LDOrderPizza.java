package com.icboluo.designpattern.create.factory.factorymethod.order;

import com.icboluo.designpattern.create.factory.factorymethod.pizza.LDCheesePizza;
import com.icboluo.designpattern.create.factory.factorymethod.pizza.LDPepperPizza;
import com.icboluo.designpattern.create.factory.factorymethod.pizza.Pizza;

/**
 * @author icboluo
 * @date 2020/12/5 18:35
 */
public class LDOrderPizza extends OrderPizza {
    public LDOrderPizza(String orderType) {
        super(orderType);
    }

    @Override
    Pizza createPizza(String orderType) {
        Pizza pizza = null;
        if ("cheese".equals(orderType)) {
            pizza = new LDCheesePizza();
        } else if ("pepper".equals(orderType)) {
            pizza = new LDPepperPizza();
        }
        return pizza;
    }
}
