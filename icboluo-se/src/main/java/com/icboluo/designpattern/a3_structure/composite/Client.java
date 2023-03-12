package com.icboluo.designpattern.a3_structure.composite;

/**
 * @author icboluo
 * @since 2022-11-01 13:40
 */
class Client {
    public static void main(String[] args) {
        University university = new University("清华", "985");

        College college1 = new College("计算机学院", "计算机...");
        College college2 = new College("艺术学院", "艺术...");

        Department department1 = new Department("java", "java...");
        Department department2 = new Department("c++", "c++...");
        Department department3 = new Department("美术", "美术...");
        Department department4 = new Department("唱歌", "唱歌...");

        university.add(college1);
        university.add(college2);

        college1.add(department1);
        college1.add(department2);
        college2.add(department3);
        college2.add(department4);

        university.print();
    }
}
