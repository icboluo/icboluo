package com.icboluo.sheji.dongtaidaili.zhuangshizhe.demo01;

import com.icboluo.sheji.PrintInterface;
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
