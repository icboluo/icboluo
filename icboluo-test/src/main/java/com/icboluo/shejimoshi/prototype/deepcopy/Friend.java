package com.icboluo.shejimoshi.prototype.deepcopy;

import lombok.AllArgsConstructor;

import java.io.Serializable;

/**
 * @author icboluo
 * @date 2020/11/7 20:45
 */
@AllArgsConstructor
public class Friend implements Cloneable, Serializable {
    private int age;
    private String name;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
