package com.icboluo.designpattern.structure.proxy.staticproxy;

/**
 * @author icboluo
 * @since 2020/11/15 20:04
 */
public class TeacherDaoImpl implements TeacherDao {
    @Override
    public void teacher() {
        System.out.println("老师在上课");
    }
}
