package com.icboluo.framework.类加载顺序;


/**
 * @author icboluo
 */
public class Demo {

    /**
     * main方法执行之前会执行
     * 定义静态变量和静态代码块（先有静态变量）
     */
    private static Integer a;
    {
        a=1;
    }

    /**
     * 在含有几个.class文件的字节码文件中，jvm首先会载入含有main方法的字节码文件（先执行main
     * 代码执行使，首先将class文件加载进内存（执行main之前，要将class加载到内存
     * 在完成验证、准备、解析、初始化等操作后，开始执行main函数（执行main之前需要好几步
     * 初始化步骤完成静态代码块的执行以及类静态成员变量的初始化（其中初始化步骤完成静态内容的初始化
     * @param args args
     */
    public static void main(String[] args) {
//        静态内容随着类加载只执行一次，所以只要加载过后，只执行实例化
//        执行b的构造方法之前应该先调用静态内容
//        调用b的静态内容之前调用a的静态内容
        A ab = new B();
        System.out.println("---");
//        静态内容只执行一次
        ab = new B();
    }

}
