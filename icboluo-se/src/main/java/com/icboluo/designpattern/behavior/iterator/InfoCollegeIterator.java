package com.icboluo.designpattern.behavior.iterator;

import java.util.Iterator;
import java.util.List;

/**
 * 信息学院迭代器，可以做为内部类，简洁一点
 *
 * @author icboluo
 * @since 2022-04-05 23:56
 */
public class InfoCollegeIterator implements Iterator<Profession> {
    List<Profession> professionList;

    int idx = 0;

    public InfoCollegeIterator(List<Profession> professionList) {
        this.professionList = professionList;
    }

    @Override
    public boolean hasNext() {
        return idx < professionList.size();
    }

    @Override
    public Profession next() {
        return professionList.get(idx++);
    }

    @Override
    public void remove() {
        Iterator.super.remove();
    }
}
