package com.icboluo.designpattern.a3_structure.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * @author icboluo
 * @since 2022-11-01 13:34
 */
public class University extends OrganizationComponent {
    List<OrganizationComponent> organizationComponents = new ArrayList<>();

    public University(String name, String desc) {
        super(name, desc);
    }

    @Override
    protected void add(OrganizationComponent organizationComponent) {
        // 业务中 college的add和university不一样
        organizationComponents.add(organizationComponent);
    }

    @Override
    protected void remove(OrganizationComponent organizationComponent) {
        organizationComponent.remove(organizationComponent);
    }

    @Override
    protected void print() {
        System.out.println(getName());
        // 递归打印
        organizationComponents.forEach(OrganizationComponent::print);
    }
}
