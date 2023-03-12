package com.icboluo.designpattern.a4_behavior.iterator;

import java.util.Iterator;

/**
 * @author icboluo
 * @since 2022-04-06 0:01
 */
public class ComputerCollege implements College<Profession> {

    Profession[] professions;
    /**
     * 当前数组对象个数
     */
    int numOfDepartment = 0;

    public ComputerCollege() {
        professions = new Profession[5];
        addProfession("JAVA专业", "JAVA专业");
        addProfession("PHP专业", "PHP专业");
        addProfession("C++专业", "C++专业");
        addProfession("GO专业", "GO专业");
    }

    @Override
    public String getName() {
        return "计算机学院";
    }

    @Override
    public void addProfession(String name, String desc) {
        Profession profession = new Profession(name, desc);
        professions[numOfDepartment++] = profession;
    }

    @Override
    public Iterator<Profession> createIterator() {
        return new ComputerCollegeIterator(professions);
    }
}
