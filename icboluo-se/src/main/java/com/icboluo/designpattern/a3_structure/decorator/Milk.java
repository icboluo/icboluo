package com.icboluo.designpattern.a3_structure.decorator;

/**
 * @author icboluo
 * @since 2023-03-02 21:59
 */
public class Milk extends Decorator {
    public Milk(Drink drink) {
        super(drink);
        setDescription("牛奶");
        setPrice(2);
    }
}
