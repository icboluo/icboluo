package com.icboluo.designpattern.structure.proxy.cglib;

/**
 * @author icboluo
 * @since 2020/11/15 20:34
 */
public class Client {
    public static void main(String[] args) {
        TeacherDaoImpl teacherDao = new TeacherDaoImpl();
        TeacherDaoImpl proxyInstance = (TeacherDaoImpl) new ProxyFactory(teacherDao).getProxyInstance();
        proxyInstance.teach();
    }
}
