package com.icboluo.designpattern.proxy.dynamic;

/**
 * @author icboluo
 * @date 2020/11/15 20:14
 */
public class TeacherDaoImpl implements TeacherDao {
    @Override
    public void teach() {
        System.out.println("老师在上课");
    }

    @Override
    public void sayHello(String name) {
        System.out.println(name);
    }
}
