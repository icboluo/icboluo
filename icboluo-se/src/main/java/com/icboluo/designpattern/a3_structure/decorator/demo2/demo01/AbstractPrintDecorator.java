package com.icboluo.designpattern.a3_structure.decorator.demo2.demo01;

import com.icboluo.designpattern.object.PrintInterface;
import lombok.AllArgsConstructor;

/**
 * @author icboluo
 * @since 2020-08-04 10:40
 */
@AllArgsConstructor
abstract class AbstractPrintDecorator implements PrintInterface {
    protected PrintInterface printInterface;

    @Override
    public void print() {
        printInterface.print();
    }
}
