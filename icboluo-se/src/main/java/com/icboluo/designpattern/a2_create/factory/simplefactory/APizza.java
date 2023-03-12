package com.icboluo.designpattern.a2_create.factory.simplefactory;

/**
 * @author icboluo
 * @since 2020/10/21 19:55
 */
public class APizza extends Pizza {
    @Override
    public void prepare() {

        System.out.println(" a pizza 准备好了");
    }
}
