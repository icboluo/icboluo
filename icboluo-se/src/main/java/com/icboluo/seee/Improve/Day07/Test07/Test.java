package com.icboluo.seee.Improve.Day07.Test07;

 class Test {
    public static void main(String[] args) {
        //实现类对象
        new Thread(new MyRunnable()).start();
        //匿名实现类
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "匿名实现类执行");
            }
        }).start();
        //匿名简化写法
        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + "简写执行了");
        }).start();
//        抽象方法的参数列表  分隔符  抽象方法的方法体
    }
}
