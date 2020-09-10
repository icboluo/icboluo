package com.icboluo.designpattern.ocp5.improve;

import lombok.AllArgsConstructor;

/**
 * @author icboluo
 * @date 2020-09-02 17:21
 */
@AllArgsConstructor
 abstract class Shape {
    int myType;

    public abstract void draw();
}
