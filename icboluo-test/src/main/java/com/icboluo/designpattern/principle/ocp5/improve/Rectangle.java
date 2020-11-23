package com.icboluo.designpattern.principle.ocp5.improve;

/**
 * @author icboluo
 * @date 2020-09-02 17:25
 */
 class Rectangle extends Shape {
    Rectangle() {
        super(1);
    }

    @Override
    public void draw() {
        System.out.println("矩形");
    }
}
