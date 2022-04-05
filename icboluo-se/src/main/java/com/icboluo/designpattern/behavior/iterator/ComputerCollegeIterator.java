package com.icboluo.designpattern.behavior.iterator;

import java.util.Iterator;

/**
 * 计算机学院
 *
 * @author icboluo
 * @since 2022-04-05 23:52
 */
public class ComputerCollegeIterator implements Iterator<Profession> {

    Profession[] professions;
    /**
     * 遍历位置
     */
    int position = 0;

    public ComputerCollegeIterator(Profession[] professions) {
        this.professions = professions;
    }

    @Override
    public boolean hasNext() {
        return position < professions.length && professions[position] != null;
    }

    @Override
    public Profession next() {
        return professions[position++];
    }

    @Override
    public void remove() {
        Iterator.super.remove();
    }
}
