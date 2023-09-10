package com.icboluo.spring.util;

import com.icboluo.spring.bean.Student;

public class InstanceFactory {
    public Student getStudent() {
        return new Student();
    }
}
