package com.icboluo.designpattern.principle.segregation2.imporve;

/**
 * @author icboluo
 * @since 2020-09-01 17:48
 */
class ClassD implements interface1,interface3 {
    @Override
    public void operation1() {
        System.out.println("d+1");
    }


    @Override
    public void operation4() {
        System.out.println("d+4");
    }

    @Override
    public void operation5() {
        System.out.println("d+5");
    }
}
