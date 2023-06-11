package com.icboluo.designpattern.a3_structure.proxy.dynamic1;

/**
 * @author icboluo
 * @since 2020/11/15 20:13
 */
public class Client {
    public static void main(String[] args) {
        TeacherDao teacherDao = new TeacherDaoImpl();
        ProxyFactory proxyFactory = new ProxyFactory(teacherDao);
        TeacherDao proxyInstance = (TeacherDao) proxyFactory.getProxyInstance();
        // 动态生成了代理对象 class jdk.proxy1.$Proxy0
        System.out.println(proxyInstance.getClass());
        // 通过代理对象调用目标对象的方法
        proxyInstance.teach();
        proxyInstance.sayHello("123");
    }
}
