package com.icboluo.designpattern.a2_create.factory.factorymethod.order;

import com.icboluo.designpattern.a2_create.factory.factorymethod.pizza.BJCheesePizza;
import com.icboluo.designpattern.a2_create.factory.factorymethod.pizza.BJPepperPizza;
import com.icboluo.designpattern.a2_create.factory.factorymethod.pizza.Pizza;

/**
 * @author icboluo
 * @since 2020/12/5 18:35
 */
public class BJOrderPizza extends OrderPizza {

    public BJOrderPizza(String orderType) {
        super(orderType);
    }

    @Override
    Pizza createPizza(String orderType) {
        Pizza pizza = null;
        if ("cheese".equals(orderType)) {
            pizza = new BJCheesePizza();
        } else if ("pepper".equals(orderType)) {
            pizza = new BJPepperPizza();
        }
        return pizza;
    }
}
