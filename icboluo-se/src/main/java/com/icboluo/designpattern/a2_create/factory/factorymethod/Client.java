package com.icboluo.designpattern.a2_create.factory.factorymethod;

import com.icboluo.designpattern.a2_create.factory.factorymethod.order.BJOrderPizza;

/**
 * @author icboluo
 * @since 2020/10/27 19:56
 */
public class Client {
    public static void main(String[] args) {
        new BJOrderPizza("cheese");
    }
}
