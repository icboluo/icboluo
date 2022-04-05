package com.icboluo.designpattern.create.factory.abstractfactory.order;

import com.icboluo.designpattern.create.factory.abstractfactory.pizza.Pizza;

/**
 * @author icboluo
 * @since 2020/12/5 18:55
 */
public class OrderPizza {

    AbsFactory factory;

    public OrderPizza(AbsFactory factory, String orderType) {
        setFactory(factory, orderType);
    }

    private void setFactory(AbsFactory factory,String orderType) {
        this.factory = factory;
        Pizza pizza = factory.creatPizza(orderType);
        pizza.prepare();
    }
}
