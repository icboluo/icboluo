package com.icboluo.designpattern.structure.adapter.classadapter;

/**
 * @author icboluo
 * @since 2020/11/7 21:27
 */
public class Client {
    public static void main(String[] args) {
        Phone phone = new Phone();
        phone.charging(new VoltageAdapter());

    }
}
