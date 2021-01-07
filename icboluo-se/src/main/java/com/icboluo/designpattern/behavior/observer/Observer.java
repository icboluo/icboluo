package com.icboluo.designpattern.behavior.observer;

/**
 * 观察者接口
 *
 * @author icboluo
 */
public interface Observer {

    void update(float temperature, float pressure, float humidity);
}
