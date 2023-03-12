package com.icboluo.designpattern.a3_structure.composite;

/**
 * @author icboluo
 * @since 2022-11-01 13:34
 */
public class Department extends OrganizationComponent {

    public Department(String name, String desc) {
        super(name, desc);
    }

    @Override
    protected void print() {
        System.out.println(getName());
    }
}
