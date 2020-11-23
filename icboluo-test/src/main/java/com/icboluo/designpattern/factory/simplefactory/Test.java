package com.icboluo.designpattern.factory.simplefactory;

/**
 * @author icboluo
 * @date 2020/10/21 19:53
 */
public class Test {
    public static void main(String[] args) {
        Factory factory = new Factory();
        Pizza a = factory.create('a');
        a.prepare();
    }
}
