package com.icboluo.designpattern.dongtaidaili.dtdl;

import com.icboluo.common.print.Print01;
import com.icboluo.common.print.PrintInterface;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author icboluo
 * @date 2020-08-03 17:24
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
