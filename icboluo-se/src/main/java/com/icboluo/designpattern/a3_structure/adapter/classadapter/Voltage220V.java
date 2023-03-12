package com.icboluo.designpattern.a3_structure.adapter.classadapter;

/**
 * 被适配的类
 *
 * @author icboluo
 * @since 2020/11/7 21:21
 */
public class Voltage220V {

    int outPut220V() {
        int src = 220;
        System.out.println("电压 " + src);
        return src;
    }
}
