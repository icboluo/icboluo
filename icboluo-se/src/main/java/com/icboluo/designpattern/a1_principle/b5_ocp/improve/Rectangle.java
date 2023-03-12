package com.icboluo.designpattern.a1_principle.b5_ocp.improve;

/**
 * @author icboluo
 * @since 2020-09-02 17:25
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
