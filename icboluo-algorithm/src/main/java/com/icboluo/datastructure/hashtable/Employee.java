package com.icboluo.datastructure.hashtable;

/**
 * @author icboluo
 * @since 2020/6/14 14:19
 */
public class Employee {
    public int id;
    public String name;
    public Employee next;

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
