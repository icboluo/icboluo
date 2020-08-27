package com.icboluo.seee.Improve.Day07.teacher.test4;

public class Test1 {
    public static void main(String[] args) {

        // 循环创建 50 个任务
        for (int i = 0; i < 50; i++) {
            Runnable task = new Runnable(){
                @Override
                public void run() {
                    int sum = 0;
                    for (int j = 1; j <= 100; j++) {
                        sum += j;
                    }
                    System.out.println(Thread.currentThread().getName() + " sum = " + sum);
                }
            };

            // 执行任务
            Thread t = new Thread(task);
            t.start();
        }
    }
}
