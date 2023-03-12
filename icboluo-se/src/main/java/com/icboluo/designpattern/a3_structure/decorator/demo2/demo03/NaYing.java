package com.icboluo.designpattern.a3_structure.decorator.demo2.demo03;

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
