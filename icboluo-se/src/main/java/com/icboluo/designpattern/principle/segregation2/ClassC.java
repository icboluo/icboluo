package com.icboluo.designpattern.principle.segregation2;

/**
 * @author icboluo
 * @date 2020-09-01 17:50
 */
public class ClassC {
    public void depend1(interface1 interface1) {
        interface1.operation1();
    }

    public void depend4(interface1 interface1) {
        interface1.operation4();
    }

    public void depend5(interface1 interface1) {
        interface1.operation5();
    }
}
