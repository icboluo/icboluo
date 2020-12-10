package com.icboluo.designpattern.structure.adapter.objectadapter;

import lombok.AllArgsConstructor;

/**
 * 适配器
 *
 * @author icboluo
 * @date 2020/11/7 21:23
 */
@AllArgsConstructor
public class VoltageAdapter implements Voltage5V {

    /**
     * 聚合220v电压
     */
    private Voltage220V voltage220V;


    @Override
    public int outPut5V() {
        int dst = 0;
        if (voltage220V != null) {
            int src = voltage220V.outPut220V();
            System.out.println("使用对象适配器进行转换");
            dst = src / 44;
            System.out.println("适配完成" + dst);
        }
        return dst;
    }
}
