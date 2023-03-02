package com.icboluo.designpattern.structure.visitor;

import java.util.LinkedList;
import java.util.List;

/**
 * 管理Man 和Woman
 *
 * @author icboluo
 * @since 2023-03-02 22:12
 */
public class ObjectStructure {

    private List<Person> persons = new LinkedList<>();

    /**
     * 附加
     *
     * @param person 附加评委
     */
    public void attach(Person person) {
        persons.add(person);
    }

    /**
     * 分离
     *
     * @param person 分离评委
     */
    public void detach(Person person) {
        persons.remove(person);
    }

    public void display(Action action) {
        persons.forEach(person -> person.accept(action));
    }
}
