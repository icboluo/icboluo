package com.icboluo.designpattern.principle.ocp5;

/**
 * @author icboluo
 * @since 2020-09-02 17:21
 */
class GraphicEditor {
    public void drawShape(Shape shape) {
        if (shape.myType == 1) {
            drawRectangle(shape);
        } else if (shape.myType == 2) {
            drawCircle(shape);
        }
    }

    public void drawRectangle(Shape r) {
        System.out.println("矩形");
    }

    public void drawCircle(Shape r) {
        System.out.println("圆形");
    }
}
