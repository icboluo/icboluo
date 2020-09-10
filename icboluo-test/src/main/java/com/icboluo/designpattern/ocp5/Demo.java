package com.icboluo.designpattern.ocp5;

/**
 * @author icboluo
 * @date 2020-09-02 17:20
 */
 class Demo {
    public static void main(String[] args) {
        GraphicEditor graphicEditor = new GraphicEditor();
//        新增模块太麻烦了
        graphicEditor.drawShape(new Rectangle());
        graphicEditor.drawShape(new Circle());
    }
}
