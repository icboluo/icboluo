package com.icboluo.seee.Improve.Day07.teacher.test6;

public class Test1 {
    public static void main(String[] args) {

        // 调用方法 :
        // Cook 是一个 `函数式接口`. 接收该接口的所有实现类对象.
        // keepAlive(String str, Cook cook);

        // 调用方式一 : 匿名实现类
        keepAlive("红烧牛肉", new Cook() {
            @Override
            public void makeFood(String food) {
                System.out.println("今天午饭 : " + food);
            }
        });

        // 调用方式二 : Lambda 表达式
        keepAlive("炸酱面", (String food) -> {
            System.out.println("今天晚饭 : " + food);
        });

        // 调用方式三 : 简化 Lambda
        keepAlive("包子和豆浆", f -> System.out.println("今天早饭 : " + f));
    }

    // 方法 : 将函数式接口作为方法的形参.
    public static void keepAlive(String str, Cook cook) {
        // 内部的默认实现.
        cook.makeFood(str);
    }
}
