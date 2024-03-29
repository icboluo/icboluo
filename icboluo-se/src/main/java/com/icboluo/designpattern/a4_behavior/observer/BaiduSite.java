package com.icboluo.designpattern.a4_behavior.observer;

import lombok.ToString;

@ToString
public class BaiduSite implements Observer {
    /**
     * 温度
     */
    private float temperature;
    /**
     * 气压
     */
    private float pressure;
    /**
     * 湿度
     */
    private float humidity;

    @Override
    public void update(float temperature, float pressure, float humidity) {
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        System.out.println(this);
    }
}
