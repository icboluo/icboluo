package com.icboluo.designpattern.ocp5;

/**
 * @author icboluo
 * @date 2020-09-02 17:21
 */
class GraphicEditor {
    public void drawShape(Shape shape) {
        switch (shape.myType) {
            case 1 -> drawRectangle(shape);
            case 2 -> drawCircle(shape);
        }
    }

    public void drawRectangle(Shape r) {
        System.out.println("矩形");
    }

    public void drawCircle(Shape r) {
        System.out.println("圆形");
    }
}
