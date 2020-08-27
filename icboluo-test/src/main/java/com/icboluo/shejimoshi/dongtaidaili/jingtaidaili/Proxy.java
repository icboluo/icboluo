package com.icboluo.shejimoshi.dongtaidaili.jingtaidaili;

import com.icboluo.common.print.Print01;
import com.icboluo.common.print.PrintInterface;

/**
 * @author icboluo
 * @date 2020-08-03 17:13
 */
class Proxy implements PrintInterface {
    private PrintInterface pi = new Print01();

    @Override
    public void print() {
        System.out.println("before");
        pi.print();
        System.out.println("after");
    }
}
