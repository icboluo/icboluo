package com.icboluo.designpattern.structure.adapter.objectadapter;

/**
 * @author icboluo
 * @date 2020/11/7 21:27
 */
public class Client {
    public static void main(String[] args) {
        Phone phone = new Phone();
        phone.charging(new VoltageAdapter(new Voltage220V()));

    }
}
