package com.icboluo.framework.类加载顺序;

/**
 * @author icboluo
 * @date 2021-55-21 21:55
 */
public class Son extends Father {

    private static int numB;
    private int numB2;

    static {
        System.out.println("B的静态字段 : " + numB);
        System.out.println("B的静态代码块");
    }

    {
        System.out.println("B的成员变量 : " + numB2);
        System.out.println("B的非静态代码块");
    }

    public Son() {
        System.out.println("B的构造器");
    }
}
