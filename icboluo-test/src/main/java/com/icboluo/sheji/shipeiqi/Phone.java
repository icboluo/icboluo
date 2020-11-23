package com.icboluo.sheji.shipeiqi;

// 手机类
class Phone {

    public static final int V = 220;// 正常电压220v，是一个常量
    /**
     * 电压适配器
     */
    private VoltageAdapter adapter;

    // 充电
    public void charge() {
        adapter.changeVoltage();
    }

    public void setAdapter(VoltageAdapter adapter) {
        this.adapter = adapter;
    }
}