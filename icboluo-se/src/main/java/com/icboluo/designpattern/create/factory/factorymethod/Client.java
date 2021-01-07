package com.icboluo.designpattern.create.factory.factorymethod;

import com.icboluo.designpattern.create.factory.factorymethod.order.BJOrderPizza;

/**
 * @author icboluo
 * @date 2020/10/27 19:56
 */
public class Client {
    public static void main(String[] args) {
        new BJOrderPizza("cheese");
    }
}
