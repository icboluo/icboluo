package com.icboluo.designpattern.a3_structure.decorator;

/**
 * 巧克力调味品
 *
 * @author icboluo
 * @since 2023-03-02 21:54
 */
class Chocolate extends Decorator {
    public Chocolate(Drink drink) {
        super(drink);
        setDescription("巧克力");
        setPrice(3);
    }
}
