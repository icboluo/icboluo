package com.icboluo.designpattern.structure.adapter.interfaceadapter;

/**
 * @author icboluo
 * @date 2020/12/10 22:26
 */
public class Client {
    public static void main(String[] args) {
        AbsAdapter absAdapter = new AbsAdapter() {
            @Override
            public void m2() {
                System.out.println("m2方法执行了");
            }
        };
    }
}
