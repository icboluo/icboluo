package com.icboluo.designpattern.dongtaidaili.zhuangshizhe.demo02;


/**
 * BufferedReader(FileReader)功能：底层封装了一个8192长度的字符数组缓冲区，增强了字符输入流的读取的效率。
 *
 * @author icboluo
 * @date 2020-08-04 13:07
 */
 class Demo {
    public static void main(String[] args) {
//        每一个装饰者都继承被装饰者，构造方法赋值给装饰者的成员变量（被装饰者），用被装饰者接收，重写被装饰方法
        Food food = new Bread(new Vegetable(new Food("香肠")));
        System.out.println(food.make());
    }
}
