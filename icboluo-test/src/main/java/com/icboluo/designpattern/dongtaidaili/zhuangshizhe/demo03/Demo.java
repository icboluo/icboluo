package com.icboluo.designpattern.dongtaidaili.zhuangshizhe.demo03;


/**
 * @author icboluo
 * @date 2020-08-12 16:42
 */
class Demo {
    public static void main(String[] args) {
        NaYing naYing = new NaYing();
        JingJiRen jingJiRen = new JingJiRen(naYing);
        jingJiRen.sing(250);
        jingJiRen.eat();
    }
}
