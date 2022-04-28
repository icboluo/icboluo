package com.icboluo.base;

/**
 * @author icboluo
 * @since 2022-04-28 23:33
 */
public class StudentTest {
    public static void main(String[] args) {
        Student student1 = new Student();
        student1.attendClass1();
        Student student2 = new Student();
        student2.name = "张三";
        student2.attendClass2();
    }
}
