package com.icboluo.se.annotation.demo02;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author icboluo
 * @date 2020-08-12 16:56
 */
public class MyJunit {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<JunitDemo> clazz = JunitDemo.class;
        Method[] methods = clazz.getMethods();
        //遍历所有的方法，带有我们的MyTest注解的方法就运行即可
        for (Method method : methods) {
            if (method.isAnnotationPresent(MyTest.class)) {
                method.invoke(clazz.getDeclaredConstructor().newInstance());
            }
        }
    }
}
