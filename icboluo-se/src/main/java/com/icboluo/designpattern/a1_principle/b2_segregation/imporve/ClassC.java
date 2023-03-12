package com.icboluo.designpattern.a1_principle.b2_segregation.imporve;

/**
 * @author icboluo
 * @since 2020-09-01 17:50
 */
public class ClassC   {
    public void depend1(interface1 interface1) {
        interface1.operation1();
    }

    public void depend4(interface3 interface3) {
        interface3.operation4();
    }

    public void depend5(interface3 interface3) {
        interface3.operation5();
    }
}
