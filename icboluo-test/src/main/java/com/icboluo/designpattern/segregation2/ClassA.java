package com.icboluo.designpattern.segregation2;

/**
 * @author icboluo
 * @date 2020-09-01 17:50
 */
public class ClassA {
    public void depend1(interface1 interface1) {
        interface1.operation1();
    }

    public void depend2(interface1 interface1) {
        interface1.operation2();
    }

    public void depend3(interface1 interface1) {
        interface1.operation3();
    }
}
