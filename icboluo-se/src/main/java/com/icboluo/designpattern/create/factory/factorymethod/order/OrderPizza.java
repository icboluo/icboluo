package com.icboluo.designpattern.create.factory.factorymethod.order;

import com.icboluo.designpattern.create.factory.factorymethod.pizza.Pizza;

/**
 * @author icboluo
 * @date 2020/12/5 18:32
 */
public abstract class OrderPizza {

    public OrderPizza(String orderType) {
        Pizza pizza = createPizza(orderType);
        pizza.prepare();

    }

    abstract Pizza createPizza(String orderType);
}
