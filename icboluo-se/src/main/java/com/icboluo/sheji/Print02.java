package com.icboluo.sheji;

/**
 * @author icboluo
 * @date 2020-08-03 17:47
 */
public class Print02 implements PrintInterface {

    @Override
    public void print() {
        System.out.println("Print02中的print方法执行了");
    }

    @Override
    public void print(String msg) {
        System.out.println("Print02中的print方法执行了" + msg);
    }
}
