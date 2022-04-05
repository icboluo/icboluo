package com.icboluo.designpattern.create.factory.abstractfactory;

import com.icboluo.designpattern.create.factory.abstractfactory.order.BJFactory;
import com.icboluo.designpattern.create.factory.abstractfactory.order.OrderPizza;

/**
 * @author icboluo
 * @since 2020/10/27 19:55
 */
public class Client {
    public static void main(String[] args) {
        new OrderPizza(new BJFactory(), "cheese");
    }
}
