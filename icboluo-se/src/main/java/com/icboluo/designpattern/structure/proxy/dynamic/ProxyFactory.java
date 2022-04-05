package com.icboluo.designpattern.structure.proxy.dynamic;

import lombok.AllArgsConstructor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author icboluo
 * @since 2020/11/15 20:15
 */
@AllArgsConstructor
public class ProxyFactory {

    private final Object target;

    public Object getProxyInstance() {
        /**
         * ClassLoader loader：指定当前目标对象使用的类加载器，获取加载器的方法固定
         * Class<?>[] interfaces：目标对象实现的接口类型，使用泛型的方法确认类型
         * InvocationHandler h：事件处理，执行目标对象的方法，会出发事件处理器方法，会吧当前执行的目标对象方法作为参数
         */
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("jdk代理开始");
                        Object invoke = method.invoke(target, args);
                        System.out.println("jdk代理结束");
                        return invoke;
                    }
                });

    }
}
