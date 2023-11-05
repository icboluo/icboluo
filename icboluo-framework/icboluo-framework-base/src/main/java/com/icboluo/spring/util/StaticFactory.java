package com.icboluo.spring.util;

import com.icboluo.spring.bean.StudentA;

public class StaticFactory {
    public static StudentA getStudent() {
        return new StudentA();
    }
}
