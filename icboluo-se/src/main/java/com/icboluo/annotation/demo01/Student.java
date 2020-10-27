package com.icboluo.annotation.demo01;

import lombok.Data;

@MyAnnotation(18)
@Data
class Student {
    String name;
    int age;

    @MyAnnotation(value = 18, name = "刘备")
    private Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Deprecated
    @MyAnnotation(value = 18, name = "刘备")
    public void show(int i) {
        System.out.println("有参的show方法" + i);
    }

}
