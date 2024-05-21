package com.icboluo.util.excel;

import lombok.AllArgsConstructor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * cglib 代理
 *
 * @author icboluo
 * @since 2024-04-02 0:36
 */
@AllArgsConstructor
public class ListenerProxyFactory implements MethodInterceptor {
    private final Object target;

    /**
     * 获取代理对象
     *
     * @return 一个代理对象（是target的代理对象）
     */
    public Object getProxyInstance() {
        // 创建一个工具类
        Enhancer enhancer = new Enhancer();
        // 设置父类
        enhancer.setSuperclass(target.getClass());
        // 设置回调函数
        enhancer.setCallback(this);
        // 创建子类对象（代理对象）
        return enhancer.create();
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        if (!"doAfterAllAnalysed".equalsIgnoreCase(method.getName())) {
            return method.invoke(target, args);
        }
        return null;
    }
}
