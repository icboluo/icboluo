package com.icboluo.annotation.demo01;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 元注解：注解的注解
 * <p>元注解有以下5种
 * <p>Retention 表示注解存在阶段是保留在源码（编译期），字节码（类加载）或者运行期（JVM中运行）
 * <p>Target 表示我们的注解作用的范围就比较具体了，可以是类，方法，方法参数变量等
 * <p>Document 它的作用是能够将注解中的元素包含到 Javadoc 中去
 * <p>Inherited 一个被@Inherited注解了的注解修饰了一个父类，如果他的子类没有被其他注解修饰，则它的子类也继承了父类的注解
 * <p>Repeatable 被这个元注解修饰的注解可以同时作用一个对象多次，但是每次作用注解又可以代表不同的含义
 *
 * <p> 注解属性类型可以有以下列出的类型
 * <p>1.基本数据类型
 * <p>2.String
 * <p>3.枚举类型
 * <p>4.注解类型
 * <p>5.Class类型
 * <p>6.以上类型的一维数组类型
 *
 * 注解的属性的数据类型：只能是基本数据类型，String，Class对象，枚举，注解，还有以上所有类型的一维数组
 * AnnotatedElement：定义了与注解解析相关的方法，常用方法以下四个：
 * <p>
 * AnnotatedElement是一个接口，它的实现类有Class，构造器，方法，属性，所以反射相关的类都有解析注解的功能。
 *
 * @author icboluo
 * @date 2020-08-12 17:05
 */
class Demo {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
                /*
        依赖注入底层实现：
        需求：将Student类的public修饰的有参的show方法上的MyAnnotation注解中的value属性值获取到
        并将这个value值，作为参数传递给show方法，并让show方法执行起来。
         */
        //获取该show方法对象
        Class<Student> clazz = Student.class;
        Method showMethod = clazz.getMethod("show", int.class);
//         判断当前对象是否有指定的注解MyAnnotation，有则返回true，否则返回false。
        boolean result = showMethod.isAnnotationPresent(MyAnnotation.class);
        if (result) {
//            获得当前对象上指定的注解对象
//            Annotation[] getAnnotations(); 获得当前对象及其从父类上继承的所有的注解对象
//            Annotation[] getDeclaredAnnotations();获得当前对象上所有的注解对象，不包括父类的
            MyAnnotation annotation = showMethod.getAnnotation(MyAnnotation.class);
            System.out.println(annotation.name() + annotation.value());
//            将注解的value值作为参数传递给show方法，让它执行
            showMethod.invoke(clazz.getDeclaredConstructor().newInstance(), annotation.value());
        }
    }
}
