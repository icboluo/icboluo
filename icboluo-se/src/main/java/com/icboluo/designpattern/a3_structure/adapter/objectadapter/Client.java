package com.icboluo.designpattern.a3_structure.adapter.objectadapter;

/**
 * @author icboluo
 * @since 2020/11/7 21:27
 */
public class Client {
    public static void main(String[] args) {
        Phone phone = new Phone();
        phone.charging(new VoltageAdapter(new Voltage220V()));

    }
}
