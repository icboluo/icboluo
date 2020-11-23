package com.icboluo.designpattern.principle.segregation2.imporve;

/**
 * @author icboluo
 * @date 2020-09-01 17:46
 */
class Demo {
    public static void main(String[] args) {
        ClassA a = new ClassA();
//        a类通过接口去依赖b类
        a.depend1(new ClassB());
        a.depend2(new ClassB());
        a.depend3(new ClassB());

        ClassC c = new ClassC();
        c.depend1(new ClassD());
        c.depend4(new ClassD());
        c.depend5(new ClassD());

    }
}
