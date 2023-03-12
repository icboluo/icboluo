package com.icboluo.designpattern.a4_behavior.observer;

/**
 * @author icboluo
 */
public class Client {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();

        Observer currentConditions = new CurrentConditions();
        weatherData.registerObserver(currentConditions);

        Observer baiduSite = new BaiduSite();
        weatherData.registerObserver(baiduSite);

        weatherData.setData(100F, 30F, 30.3F);
        weatherData.removeObserver(baiduSite);
        weatherData.setData(100F, 30F, 30.3F);
    }
}
