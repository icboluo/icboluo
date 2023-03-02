package com.icboluo.designpattern.create.prototype.simple;

import lombok.AllArgsConstructor;

/**
 * 浅拷贝
 *
 * @author icboluo
 * @since 2023-03-02 22:17
 */
@AllArgsConstructor
class SheepShallowCopy implements Cloneable {

    private String name;

    private Integer age;

    private String color;

    @Override
    protected SheepShallowCopy clone() throws CloneNotSupportedException {
        return (SheepShallowCopy) super.clone();
    }
}
