package com.icboluo.designpattern.behavior.observer;

import lombok.ToString;

/**
 * 观察者
 *
 * @author icboluo
 */
@ToString
public class CurrentConditions implements Observer {
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
