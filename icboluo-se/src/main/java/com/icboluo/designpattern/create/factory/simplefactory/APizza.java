package com.icboluo.designpattern.create.factory.simplefactory;

/**
 * @author icboluo
 * @date 2020/10/21 19:55
 */
public class APizza extends Pizza {
    @Override
    public void prepare() {

        System.out.println(" a pizza 准备好了");
    }
}
