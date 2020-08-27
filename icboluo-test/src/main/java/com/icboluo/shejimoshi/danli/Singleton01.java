package com.icboluo.shejimoshi.danli;

public class Singleton01 {
    private static Singleton01 instance = new Singleton01();

    public static Singleton01 getInstance() {
        return instance;
    }
}
