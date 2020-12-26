package com.icboluo.designpattern.structure.proxy.cglib;


import lombok.AllArgsConstructor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author icboluo
 * @date 2020/11/15 20:35
 */
@AllArgsConstructor
public class ProxyFactory implements MethodInterceptor {

    private final Object target;

    /**
     * @return 一个代理对象（是target的代理对象）
     */
    public Object getProxyInstance() {
//        创建一个工具类
        Enhancer enhancer = new Enhancer();
//        设置父类
        enhancer.setSuperclass(target.getClass());
//        设置回调函数
        enhancer.setCallback(this);
//        创建子类对象（代理对象）
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("cglib代理开始");
        Object retVal = method.invoke(target, objects);
        System.out.println("cglib代理结束");
        return retVal;
    }
}
