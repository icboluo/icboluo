package com.icboluo.designpattern.structure.decorator;

import lombok.AllArgsConstructor;

/**
 * 装饰器
 *
 * @author icboluo
 * @since 2023-03-02 21:51
 */
@AllArgsConstructor
class Decorator extends Drink {
    private Drink drink;

    @Override
    public Integer cost() {
        return super.getPrice() + drink.cost();
    }

    @Override
    public String getDescription() {
        return super.getDescription() + "(" + getPrice() + ")" + " && " + drink.getDescription();
    }
}
