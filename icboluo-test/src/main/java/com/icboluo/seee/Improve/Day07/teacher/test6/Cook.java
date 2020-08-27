package com.icboluo.seee.Improve.Day07.teacher.test6;

// 函数式接口的语法检查 :  有, 且仅有一个抽象方法的接口.
@FunctionalInterface

public interface Cook {

    // 抽象方法
    void makeFood(String food);
}
