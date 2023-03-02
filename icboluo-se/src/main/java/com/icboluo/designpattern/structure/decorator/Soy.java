package com.icboluo.designpattern.structure.decorator;

/**
 * @author icboluo
 * @since 2023-03-02 22:00
 */
public class Soy extends Decorator {
    public Soy(Drink drink) {
        super(drink);
        setDescription("Soy");
        setPrice(1);
    }
}
