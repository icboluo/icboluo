package com.icboluo.shejimoshi.adapter.classadapter;

/**
 * @author icboluo
 * @date 2020/11/7 21:27
 */
public class Client {
    public static void main(String[] args) {
        Phone phone = new Phone();
        phone.charging(new VoltageAdapter());

    }
}
