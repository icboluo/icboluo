package com.icboluo.designpattern.a3_structure.decorator;

/**
 * 咖啡
 *
 * @author icboluo
 * @since 2023-03-02 21:56
 */
class Coffee extends Drink {
    @Override
    public Integer cost() {
        return super.getPrice();
    }
}
