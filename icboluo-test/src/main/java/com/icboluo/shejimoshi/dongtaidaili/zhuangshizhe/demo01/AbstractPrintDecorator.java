package com.icboluo.shejimoshi.dongtaidaili.zhuangshizhe.demo01;

import com.icboluo.common.print.PrintInterface;
import lombok.AllArgsConstructor;

/**
 * @author icboluo
 * @date 2020-08-04 10:40
 */
@AllArgsConstructor
abstract class AbstractPrintDecorator implements PrintInterface {
    protected PrintInterface printInterface;

    @Override
    public void print() {
        printInterface.print();
    }
}
