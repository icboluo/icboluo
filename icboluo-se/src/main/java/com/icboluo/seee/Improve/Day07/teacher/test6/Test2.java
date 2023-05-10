package com.icboluo.seee.Improve.Day07.teacher.test6;

public class Test2 {
    public static void main(String[] args) {

        // 调用方法 :
        // 方式一 : 匿名实现类
        testCalculator(10, 20, new Calculator() {
            @Override
            public int calcSum(int num1, int num2) {
                int result = num1 + num2;
                return result;
            }
        });

        // 方式二 : Lambda 表达式
        testCalculator(100, 200, (int num1, int num2) -> { return num1 + num2; });

        // 方式三 : 简化 Lambda 表达式
        testCalculator(1000, 2000, (a, b) -> a + b);
    }

    // 方法 : 将函数式接口作为方法对方形参
    public static void testCalculator(int n1, int n2, Calculator calculator) {
        // 内部方法的默认实现
        int sum = calculator.calcSum(n1, n2);
        System.out.println("sum = " + sum);
    }
}
