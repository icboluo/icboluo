package com.icboluo.designpattern.a1_principle.b5_ocp.improve;

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
