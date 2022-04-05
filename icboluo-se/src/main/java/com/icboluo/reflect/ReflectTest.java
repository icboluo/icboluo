package com.icboluo.reflect;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * 反射的框架，可以创建任意类的对象，可以执行任意方法
 * 框架代码是不能够修改的，不能写死（不能直接new）
 *
 * @author icboluo
 * @since 2020-09-01 16:31
 */
class ReflectTest {
    public static void main(String[] args) throws Exception {
        Properties pro = new Properties();
//        获取配置文件并加载到properties中
        ClassLoader classLoader = ReflectTest.class.getClassLoader();
        InputStream is = classLoader.getResourceAsStream("application.yml");
        pro.load(is);
//        获取配置文件中的属性
        String className = pro.getProperty("className");
        String methodName = pro.getProperty("methodName");
//      加载该类进内存
        Class<?> clazz = Class.forName(className);
//        创建对象调用方法
        Object obj = clazz.getDeclaredConstructor().newInstance();
        Method method = clazz.getMethod(methodName);
        method.invoke(obj);
    }
}
