package com.icboluo.designpattern.product_consumer;

/**
 * @author icboluo
 */
public class TestDeam {

    public static void main(String[] args) {
        SyncStack stack = new SyncStack();
        Consumer p = new Consumer(stack);
        Producter c = new Producter(stack);

        new Thread(p).start();
        new Thread(c).start();

    }
}
