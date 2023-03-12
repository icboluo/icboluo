package com.icboluo.designpattern.a3_structure.decorator;

/**
 * 咖啡吧
 *
 * @author icboluo
 * @since 2023-03-02 21:56
 */
class CoffeeBar {
    public static void main(String[] args) {
        Drink order = new LongBlack();
        System.out.println(order.getDescription() + " :-->" + order.cost());

        Drink milk = new Milk(order);
        System.out.println(milk.getDescription() + " :-->" + milk.cost());

        Drink chocolate = new Chocolate(milk);
        System.out.println(chocolate.getDescription() + " :-->" + chocolate.cost());

        Drink chocolate2 = new Chocolate(chocolate);
        System.out.println(chocolate2.getDescription() + " :-->" + chocolate2.cost());

    }
}
