package com.icboluo.designpattern.principle.inversion3.improve;

/**
 * @author icboluo
 * @date 2020-09-02 16:04
 */
 class Demo {
    public static void main(String[] args) {
        Person person = new Person();
//        如果获取的消息是微信、短信
//        需要新增一个类，并且person需要增加相应的接收方法
        person.receive(new Email());
//        依赖倒置，提供接口让其他的类去继承，person直接接收接口

    }

}
