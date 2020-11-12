package com.icboluo.shejimoshi.adapter.classadapter;

/**
 * 被适配的类
 *
 * @author icboluo
 * @date 2020/11/7 21:21
 */
public class Voltage220V {

    int outPut220V() {
        int src = 220;
        System.out.println("电压 " + src);
        return src;
    }
}
