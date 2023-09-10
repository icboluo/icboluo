package com.icboluo.spring.util;

import com.icboluo.spring.bean.Student;

public class StaticFactory {
    public static Student getStudent() {
        return new Student();
    }
}
