package com.icboluo.annotation.demo02;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author icboluo
 * @since 2020-08-29 21:28
 */
@Pro06(className = "com.icboluo.annotation.Demo07", methodName = "show")
class Test08 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        // 获得该类的字节码文件
        Class<Test08> reflect = Test08.class;
        // 在内存中生成了一个实现该注解的实现类对象，并且该对象包含抽象方法的具体实现
        Pro06 pro = reflect.getAnnotation(Pro06.class);

        String className = pro.className();
        String methodName = pro.methodName();
        System.out.println(className + "-----" + methodName);

        Class<?> cls = Class.forName(className);
/*        过时了
        Object obj = cls.newInstance();*/
        Object obj = cls.getDeclaredConstructor().newInstance();
        Method method = cls.getMethod(methodName);
        method.invoke(obj);

    }
}
