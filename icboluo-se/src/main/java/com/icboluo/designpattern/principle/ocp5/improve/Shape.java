package com.icboluo.designpattern.principle.ocp5.improve;

import lombok.AllArgsConstructor;

/**
 * @author icboluo
 * @since 2020-09-02 17:21
 */
@AllArgsConstructor
 abstract class Shape {
    int myType;

    public abstract void draw();
}
