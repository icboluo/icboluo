package com.icboluo.designpattern.dongtaidaili.zhuangshizhe.demo03;


/**
 * @author icboluo
 */
class JingJiRen implements Singer {
    Singer singer;

    public JingJiRen(Singer singer) {
        this.singer = singer;
    }

    @Override
    public void sing(int money) {
        if (money > 200) {
            singer.sing(money);
        } else {
            System.out.println("钱不够，一边玩去");
        }
    }

    @Override
    public void eat() {
        System.out.println("那姐不约，我约");
    }
}
