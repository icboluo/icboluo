package com.icboluo.spring.util;

import com.icboluo.spring.bean.StudentA;

public class InstanceFactory {
    public StudentA getStudent() {
        return new StudentA();
    }
}
