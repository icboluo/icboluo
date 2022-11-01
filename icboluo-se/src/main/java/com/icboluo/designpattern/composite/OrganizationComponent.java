package com.icboluo.designpattern.composite;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author icboluo
 * @since 2022-11-01 13:35
 */
@AllArgsConstructor
@Data
public abstract class OrganizationComponent {
    private String name;

    private String desc;

    protected void add(OrganizationComponent organizationComponent) {
        throw new UnsupportedOperationException();
    }
    protected void remove(OrganizationComponent organizationComponent) {
        throw new UnsupportedOperationException();
    }

    protected abstract void print();
}
