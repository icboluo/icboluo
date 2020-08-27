package com.icboluo.seee.Improve.Day07;

 class Test2 {
    public static void main(String[] args) {
        new Thread(){//匿名子类
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread().getName()+"ren....."+i);

                }
            }
        }.start();
        new Thread(new Runnable() {
            @Override
            public void run() {

        for (int i = 0; i <10 ; i++) {
            System.out.println(Thread.currentThread().getName());
        }
            }
        }).start();
    }
}
