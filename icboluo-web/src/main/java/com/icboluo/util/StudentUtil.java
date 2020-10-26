package com.icboluo.util;

import com.icboluo.dataobject.Student;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;

/**
 * @author icboluo
 * @date 2020/10/27 00:06
 */
@Slf4j
public class StudentUtil {


    private static final Student student;


    @PostConstruct //@PostConstruct修饰的方法会在服务器加载Servle的时候运行，并且只会被服务器执行一次。PostConstruct在构造函数之后执行,init()方法之前执行
    public void init() {

    }
    static {
        log.debug("静态代码块加载了");
        student = new Student();


    }

    public static void print() {
        log.info(student.toString());
    }
}
