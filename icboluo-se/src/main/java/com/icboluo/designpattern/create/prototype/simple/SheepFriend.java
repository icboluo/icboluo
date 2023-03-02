package com.icboluo.designpattern.create.prototype.simple;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author icboluo
 * @since 2023-03-02 22:21
 */
@Data
@AllArgsConstructor
class SheepFriend implements Cloneable, Serializable {
    private String name;

    private Integer age;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
