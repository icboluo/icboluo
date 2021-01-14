package com.icboluo.reflect;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 获取class的3种方式
 * class.forName("全类名") :将字节码文件加载到内存中，返回class对象
 * 类名.class ：通过类名的属性class获取
 * 对象.getClass ：obj的方法获取
 *
 * @author icboluo
 * @date 2020-08-30 14:45
 */
@Slf4j
class Demo01 {
    public static void main(String[] args) throws Exception {
//        多用于配置文件
        Class<?> clazz1 = Class.forName("com.icboluo.reflect.Person");
        System.out.println("clazz1 = " + clazz1);
//        多用于参数传递
        Class<Person> clazz2 = Person.class;
        System.out.println("clazz2 = " + clazz2);
//        多用于对象获取字节码文件
        Person person = new Person();
        Class<? extends Person> clazz3 = person.getClass();
        System.out.println("clazz3 = " + clazz3);

        //他们3个都是相等的，同一个字节码文件在一次程序的运行过程中，只会被加载一次
/*//获取成员变量
        clazz1.getField();//只能获得public修饰的成员变量
        clazz1.getFields();
        clazz1.getDeclaredField();
        clazz1.getDeclaredFields();
//        获取构造方法
        clazz1.getConstructor();
        clazz1.getConstructors();
        clazz1.getDeclaredConstructor();
        clazz1.getDeclaredConstructors();
//        获取成员方法
        clazz1.getMethods();
        clazz1.getMethod();
        clazz1.getDeclaredMethod();
        clazz1.getDeclaredMethods();
//        获取类名
        clazz1.getName();*/
        Field[] declaredFields = clazz1.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            Person person1 = new Person();
            System.out.println("declaredField = " + declaredField);
//            暴力反射,忽略权限修饰符
            declaredField.setAccessible(true);
            Object obj = declaredField.get(person1);
            System.out.println("obj = " + obj);
        }

        Constructor<?> constructor = clazz1.getConstructor(String.class, int.class);
//        获取构造方法只能用参数列表来区分
        Object aa = constructor.newInstance("张三", 18);
        System.out.println("aa = " + aa);

        Method eat = clazz1.getMethod("eat");
        Person person1 = new Person();
        eat.invoke(person1);
    }
}
