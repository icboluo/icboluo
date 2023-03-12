package com.icboluo.designpattern.a3_structure.decorator.demo2.demo03;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


/*
    静态代理：固定地创建一个新的类，来特定地增强某个类的功能。（JingJiRen）
    动态代理：在程序运行起来的时候，才生产一个代理对象，从而对某个类进行增强


    装饰设计模式
        装饰者(花瓶)             被装饰者(花)                功能：增强了花的好看功能
        经纪人                     那英                      功能：增强了那英类的唱歌跳舞的金额判断功能

    前提条件：装饰者与被装饰者的功能要一致，继承同一个父类或者实现同一个接口
    ---------------------------------------------------------------------------------------------
        BufferedReader          FileReader                  功能：底层封装了一个8192长度的字符数组缓冲区，增强了字符输入流的读取的效率。

    动态代理
        代理者                     被代理者                功能：
        singerProxy                naYing                 拦截sing和dance，增强了它的金额判断
     前提条件：代理者和被代理者必须拥有相同的方法，实现同一个接口。

     装饰设计模式与动态代理的区别：
     1.装饰设计模式，重心在于增强，必须将接口或者抽象父类的所有的抽象方法都实现，很麻烦
     2.动态代理的重心在于拦截，只需要拦截想拦截的方法即可

     在java中对于一个类的方法的增强有几种方式呢？？？
     1.继承
     2.装饰设计模式
     3.动态代理

 */
class DynamicProxyDemo {
    public static void main(String[] args) {
//要求在不修改那英类，并且不创建经纪人类的基础上还是要对那英的唱歌跳舞增加金额判断功能 ,只有动态代理
        NaYing naYing = new NaYing();

        //动态代理的api
        //类加载器都使用被代理者的类加载器
//    类加载器:java程序要运行必须通过编译生成字节码文件，加载到JVM中就可以运行，这里就是使用类加载器加载的。
//注意：类加载器，将字节码文件加载到方法区后，就会在堆中产生Class对象，它是动态代理所需的参数。
        ClassLoader loader = naYing.getClass().getClassLoader();
        //被代理者的接口的类数组
        Class<?>[] interfaces = naYing.getClass().getInterfaces();
        InvocationHandler h = new InvocationHandler() {
            /*
                proxy:表示就是动态代理对象也就是singerProxy（不用）
                method:表示动态代理对象所调用的那个方法对象
                args：表示动态代理对象所调用的那个方法传入的参数
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                /*System.out.println(method.getName());
                System.out.println("参数为："+ Arrays.toString(args));
                System.out.println("invoke方法执行了");*/

                //判断下当前是否是唱歌跳舞方法，如果是就进行金额的判断增强
                if ("sing".equals(method.getName())) {
                    int money = (int) args[0];
                    if (money > 200) {
//                        naYing.sing(money);
                        method.invoke(naYing, args);
                    } else {
                        System.out.println("钱不够，一遍玩去");
                    }
                    return null;
                }
                //如果不是我们动态代理想要拦截的功能，那么就应该执行原本的功能
                return method.invoke(naYing, args);
            }
        };
        Singer singerProxy = (Singer) Proxy.newProxyInstance(loader, interfaces, h);
        //注意：代理对象调用任意方法都会导致InvocationHandler中的invoke方法执行一次
        singerProxy.sing(150);
        singerProxy.eat();
    }
}
