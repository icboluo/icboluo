package com.icboluo.designpattern.a3_structure.adapter.classadapter;

/**
 * @author icboluo
 * @since 2020/11/7 21:26
 */
public class Phone {
    public void charging(Voltage5V v) {
        if (v.outPut5V() == 5) {
            System.out.println("电压 5 v 可以充电");
        } else if (v.outPut5V() > 5) {
            System.out.println("电压过高");
        }
    }
}
