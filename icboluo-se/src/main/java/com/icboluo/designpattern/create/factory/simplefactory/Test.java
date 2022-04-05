package com.icboluo.designpattern.create.factory.simplefactory;

/**
 * @author icboluo
 * @since 2020/10/21 19:53
 */
public class Test {
    public static void main(String[] args) {
        Factory factory = new Factory();
        Pizza a = factory.create('a');
        a.prepare();
    }
}
