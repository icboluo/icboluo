package com.icboluo.sheji.dongtaidaili.zhuangshizhe.demo01;


import com.icboluo.sheji.PrintInterface;

/**
 * @author icboluo
 * @since 2020-08-04 10:42
 */
 class PrintDecorator extends AbstractPrintDecorator {
    public PrintDecorator(PrintInterface printInterface) {
        super(printInterface);
    }

    @Override
    public void print() {
        printInterface.print();
        method(printInterface);
    }

    private void method(PrintInterface decoratedShape) {
        System.out.println("装饰者方法执行了");
    }
}
