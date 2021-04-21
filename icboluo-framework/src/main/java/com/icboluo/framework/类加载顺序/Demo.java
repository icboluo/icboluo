package com.icboluo.framework.类加载顺序;


/**
 * @author icboluo
 */
public class Demo {
    public static void main(String[] args) {
//        执行b的构造方法之前应该先调用静态内容
//        调用b的静态内容之前调用a的静态内容
        A ab = new B();
        System.out.println("---");
//        静态内容只执行一次
        ab = new B();
    }

}
