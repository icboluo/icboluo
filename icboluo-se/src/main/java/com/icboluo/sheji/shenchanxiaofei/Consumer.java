package com.icboluo.sheji.shenchanxiaofei;

public class Consumer implements Runnable {

    private final SyncStack stack;

    public Consumer(SyncStack stack) {
        this.stack = stack;
    }

    @Override
    public void run() {
        for (int i = 0; i < stack.pro().length; i++) {
            String consumer = stack.pop();
            System.out.println("消费了：" + consumer);
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}