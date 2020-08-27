package com.icboluo.shejimoshi.dongtaidaili.zhuangshizhe.demo01;

import com.icboluo.common.print.Print01;
import com.icboluo.common.print.Print02;

/**
 * @author icboluo
 * @date 2020-08-04 10:48
 */
class Demo {
    public static void main(String[] args) {
/*        Print01 print01 = new Print01();
        PrintInterface print001 = new PrintDecorator(new Print01());
        PrintInterface print002 = new PrintDecorator(new Print02());
        print01.print();*/
        AbstractPrintDecorator print001 = new PrintDecorator(new Print01());
        AbstractPrintDecorator print002 = new PrintDecorator(new Print02());
        print001.print();
        print002.print();

    }
}
