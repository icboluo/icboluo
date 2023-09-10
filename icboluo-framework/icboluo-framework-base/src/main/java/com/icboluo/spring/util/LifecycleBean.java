package com.icboluo.spring.util;

public class LifecycleBean {
    public LifecycleBean() {
        System.out.println("lifecyclebean");
    }

    public void start() {
        System.out.println("start");
    }

    public void end() {
        System.out.println("end");
    }
}
