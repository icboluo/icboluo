package com.icboluo.designpattern.guanchazhe;

import com.icboluo.common.print.Print01;
import com.icboluo.common.print.Print02;

/**
 * @author icboluo
 * @date 2020-08-04 11:15
 */
class Demo {
    public static void main(String[] args) {
        Bgcz bgcz = new Bgcz();
        Print01 print01 = new Print01();
        Print02 print02 = new Print02();

        bgcz.addPrint(print01);
        bgcz.addPrint(print02);

        bgcz.notifyPerson();
    }
}

