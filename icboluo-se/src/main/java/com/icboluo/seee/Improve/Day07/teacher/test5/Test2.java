package com.icboluo.seee.Improve.Day07.teacher.test5;

public class Test2 {
    public static void main(String[] args) {

        // 匿名实现类 :
        new Thread(new Runnable() {
            @Override
            public void run() {
                int sum = 0;
                for (int i = 1; i <= 100; i++) {
                    sum += i;
                }
                System.out.println("sum = " + sum);
            }
        }).start();

        // Lambda 写法 : 简化了抽象接口的名称和抽象方法的名称.  (Runnable, run);
        new Thread(() -> {
            int sum = 0;
            for (int i = 1; i <= 100; i++) {
                sum += i;
            }
            System.out.println("sum = " + sum);
        }).start();
    }
}
