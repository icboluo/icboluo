package com.icboluo.framework.类加载顺序;

/**
 * @author icboluo
 * @since 2021-55-21 21:55
 */
public class Father {
    private static int numA;
    private int numA2;

    static {
        System.out.println("A的静态字段 : " + numA);
        System.out.println("A的静态代码块");
    }
//  实例化代码块先与构造器执行，而且是紧紧连着的
    {
        System.out.println("A的成员变量  : " + numA2);
        System.out.println("A的非静态代码块");
    }

    public Father() {
        System.out.println("A的构造器");
    }
}
