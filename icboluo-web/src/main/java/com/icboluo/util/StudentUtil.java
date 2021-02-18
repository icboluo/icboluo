package com.icboluo.util;

import com.icboluo.dataobject.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * PostConstruct 代表的是创建对象后调用
 * Profile 代表的是某个环境生效
 * 他们2个毫无关系，各自有自己的生效场景，如果类都不生效，创建对象就更不可能了
 *
 * @author icboluo
 * @date 2020/10/27 00:06
 */
@Slf4j
//需要增加这个注解才可以autowired
@Component
public class StudentUtil {

    private static Student student;

    @Autowired
    private Student studentConfig;


    @PostConstruct //@PostConstruct修饰的方法会在服务器加载Servle的时候运行，并且只会被服务器执行一次。PostConstruct在构造函数之后执行,init()方法之前执行
    public void init() {
        student = this.studentConfig;
    }

    static {
        log.debug("静态代码块加载了");
        student = new Student();
    }

    public static void print() {
        log.info(student.toString());
    }
}
