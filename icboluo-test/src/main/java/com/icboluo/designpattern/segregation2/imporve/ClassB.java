package com.icboluo.designpattern.segregation2.imporve;

/**
 * @author icboluo
 * @date 2020-09-01 17:48
 */
class ClassB implements interface1,interface2 {
    @Override
    public void operation1() {
        System.out.println("b+1");
    }

    @Override
    public void operation2() {
        System.out.println("b+2");
    }

    @Override
    public void operation3() {
        System.out.println("b+3");
    }

}
