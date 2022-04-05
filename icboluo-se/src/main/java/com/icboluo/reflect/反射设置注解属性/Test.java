package com.icboluo.reflect.反射设置注解属性;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * @author icboluo
 * @since 2020/10/27 20:14
 */
class Test {
    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException {
        Class<Student> clazz = Student.class;
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            Stu stu = field.getAnnotation(Stu.class);
            //获取 stu 这个代理实例所持有的 InvocationHandler
            InvocationHandler ih = Proxy.getInvocationHandler(stu);
            // 获取 AnnotationInvocationHandler 的 memberValues 字段
            Field hField = ih.getClass().getDeclaredField("memberValues");
            // 因为这个字段事 private final 修饰，所以要打开权限
            hField.setAccessible(true);
            // 获取 memberValues
            Map memberValues = (Map) hField.get(ih);
            memberValues.put("index", 12);
            System.out.println(stu.index());
        }
    }
}
