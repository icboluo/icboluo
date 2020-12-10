package com.icboluo.designpattern.structure.adapter.classadapter;

/**
 * 适配器
 *
 * @author icboluo
 * @date 2020/11/7 21:23
 */
//java单继承，又要继承一个src这个类，所以要求5v这个类必须是一个接口
public class VoltageAdapter extends Voltage220V implements Voltage5V {
    @Override
    public int outPut5V() {
//        缺点：src类方法在被适配中暴露出来，增加使用成本
//        有点：继承了src类，可以重写，增加了灵活性
        int src = outPut220V();
        return src / 44;
    }
}
