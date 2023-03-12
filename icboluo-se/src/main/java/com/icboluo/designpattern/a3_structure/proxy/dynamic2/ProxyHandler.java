package com.icboluo.designpattern.a3_structure.proxy.dynamic2;

import com.icboluo.designpattern.object.PrintInterface;
import lombok.AllArgsConstructor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 代理处理者
 *
 * @author icboluo
 * @since 2020-08-03 17:22
 */
@AllArgsConstructor
class ProxyHandler implements InvocationHandler {
    /**
     * 被代理的类，可以用泛型、具体类来替换
     */
    private Object object;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Before invoke " + method.getName());
        method.invoke(object, args);
        System.out.println("After invoke " + method.getName());
        return null;
    }

    public PrintInterface getActorProxy() {
        return (PrintInterface) Proxy.newProxyInstance(object.getClass().getClassLoader(), object.getClass().getInterfaces(), this);
    }
}
