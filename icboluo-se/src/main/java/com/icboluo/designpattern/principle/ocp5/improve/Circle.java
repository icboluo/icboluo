package com.icboluo.designpattern.principle.ocp5.improve;

/**
 * @author icboluo
 * @since 2020-09-02 17:26
 */
class Circle extends Shape {
    Circle() {
        super(2);
    }

    @Override
    public void draw() {
        System.out.println("圆形");
    }
}
