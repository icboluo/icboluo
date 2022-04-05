package com.icboluo.sheji.dongtaidaili.dtdl;

import com.icboluo.sheji.Print01;
import com.icboluo.sheji.PrintInterface;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author icboluo
 * @since 2020-08-03 17:24
 */
class Demo {
    public static void main(String[] args) {
        //System.getProperties().setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        PrintInterface print01 = new Print01();

        InvocationHandler handler = new ProxyHandler(print01);

        PrintInterface proxyHello = (PrintInterface) Proxy.newProxyInstance(
                print01.getClass().getClassLoader(), print01.getClass().getInterfaces(), handler);

        proxyHello.print();
    }
}
