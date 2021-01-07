package com.icboluo.sheji.dongtaidaili.zhuangshizhe.demo03;

 class NaYing implements Singer{

    @Override
    public void sing(int money) {
        System.out.println("那英收到了"+money+"钱，唱了征服");
    }

    @Override
    public void eat() {
        System.out.println("那英吃麦当劳");
    }
}
