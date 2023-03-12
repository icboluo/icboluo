package com.icboluo.designpattern.a4_behavior.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author icboluo
 * @since 2022-04-06 0:01
 */
public class InfoCollege implements College<Profession> {

    List<Profession> professionList;

    /**
     * 当前数组对象个数
     */
    int numOfDepartment = 0;

    public InfoCollege() {
        professionList = new ArrayList<>();
        addProfession("信息安全专业", "信息安全专业");
        addProfession("网络安全专业", "网络安全专业");
        addProfession("服务器安全专业", "服务器安全专业");
    }

    @Override
    public String getName() {
        return "信息学院";
    }

    @Override
    public void addProfession(String name, String desc) {
        Profession profession = new Profession(name, desc);
        professionList.add(profession);
        numOfDepartment++;
    }

    @Override
    public Iterator<Profession> createIterator() {
        return new InfoCollegeIterator(professionList);
    }
}
