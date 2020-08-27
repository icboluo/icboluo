package com.icboluo.seee.Improve.Day07;

 class Test08 {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int sum = 0;
                for (int i = 0; i < 100; i++) {
                    sum += i;
                }
                System.out.println("sum = " + sum);
            }
        }).start();
        new Thread(() -> {
            int sum = 0;
            for (int i = 0; i < 100; i++) {
                sum += i;
            }
            System.out.println("sum = " + sum);
        }).start();
    }
}
