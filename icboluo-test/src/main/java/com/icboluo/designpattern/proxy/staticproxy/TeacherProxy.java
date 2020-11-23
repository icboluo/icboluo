package com.icboluo.designpattern.proxy.staticproxy;

import lombok.AllArgsConstructor;

/**
 * 代理对象 静态代理
 *
 * @author icboluo
 * @date 2020/11/15 20:05
 */
@AllArgsConstructor
public class TeacherProxy implements TeacherDao {
    /**
     * 目标对象，通过接口来聚合
     */
    private final TeacherDao targer;

    @Override
    public void teacher() {
        System.out.println("代理开始了");
        targer.teacher();
        System.out.println("代理结束了");
    }
}
