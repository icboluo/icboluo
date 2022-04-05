package com.icboluo.designpattern.principle.ocp5.improve;

/**
 * @author icboluo
 * @since 2020-09-02 17:20
 */
 class Demo {
    public static void main(String[] args) {
        GraphicEditor graphicEditor = new GraphicEditor();
        graphicEditor.drawShape(new Rectangle());
        graphicEditor.drawShape(new Circle());
    }
}
