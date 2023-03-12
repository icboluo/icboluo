package com.icboluo.designpattern.a4_behavior.iterator;

import java.util.Iterator;

/**
 * 学院
 *
 * @author icboluo
 * @since 2022-04-05 23:59
 */
public interface College<T> {

    String getName();

    void addProfession(String name, String desc);

    Iterator<T> createIterator();
}
