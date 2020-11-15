package com.icboluo.shejimoshi.proxy.staticproxy;

/**
 * 没有修改被代理对象，对原来的功能进行扩展
 * 会生成很多的代理类，代理类和目标类都要实现相同的接口
 *
 * @author icboluo
 * @date 2020/11/15 20:08
 */
public class Client {
    public static void main(String[] args) {
        TeacherDao teacherDao = new TeacherDaoImpl();
        TeacherProxy proxy = new TeacherProxy(teacherDao);
//        执行的是代理对象的方法，代理对象再调用被代理的目标对象的方法
        proxy.teacher();
    }
}
