package com.icboluo.designpattern.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * @author icboluo
 * @since 2022-11-01 13:34
 */
public class College extends OrganizationComponent {
    List<OrganizationComponent> organizationComponents = new ArrayList<>();

    public College(String name, String desc) {
        super(name, desc);
    }

    @Override
    protected void add(OrganizationComponent organizationComponent) {
        // 业务中 college的add和university不一样
        organizationComponent.add(organizationComponent);
    }

    @Override
    protected void remove(OrganizationComponent organizationComponent) {
        organizationComponent.remove(organizationComponent);
    }

    @Override
    protected void print() {
        System.out.println(getName());
        organizationComponents.forEach(System.out::println);
    }
}
