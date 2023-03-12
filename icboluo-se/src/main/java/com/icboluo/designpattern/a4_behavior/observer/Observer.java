package com.icboluo.designpattern.a4_behavior.observer;

/**
 * 观察者接口
 *
 * @author icboluo
 */
public interface Observer {

    void update(float temperature, float pressure, float humidity);
}
