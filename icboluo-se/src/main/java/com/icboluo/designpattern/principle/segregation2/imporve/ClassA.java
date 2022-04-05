package com.icboluo.designpattern.principle.segregation2.imporve;

/**
 * @author icboluo
 * @since 2020-09-01 17:50
 */
public class ClassA   {
    public void depend1(interface1 interface1) {
        interface1.operation1();
    }

    public void depend2(interface2 interface2) {
        interface2.operation2();
    }

    public void depend3(interface2 interface2) {
        interface2.operation3();
    }

}
