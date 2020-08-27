package com.icboluo.shejimoshi.factory;

import com.icboluo.common.print.PrintInterface;

public class DemoFactory {
    public static void main(String[] args) {
        Factory factory = new Factory();
        PrintInterface print = factory.print(1);
        print.print();
    }
}
