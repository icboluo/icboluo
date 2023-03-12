package com.icboluo.designpattern.a4_behavior.iterator;

import lombok.AllArgsConstructor;

import java.util.Iterator;
import java.util.List;

/**
 * @author icboluo
 * @since 2022-04-06 0:10
 */
@AllArgsConstructor
public class OutPutImpl<T> {

    List<College<T>> collegeList;

    public void printProfession(Iterator<T> iterator) {
        while (iterator.hasNext()) {
            T next = iterator.next();
            if (next instanceof Profession profession) {
                System.out.println(profession.getName());
            }
        }
    }

    public void printCollege() {
        Iterator<College<T>> iterator = collegeList.iterator();
        while (iterator.hasNext()) {
            College<T> next = iterator.next();
            System.out.println("----------------" + next.getName() + "--------------------");
            Iterator<T> collegeIter = next.createIterator();
            printProfession(collegeIter);
        }
    }
}
