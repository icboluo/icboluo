package com.icboluo.seee.Improve.Day07.Test03;

 class Test {
    public static void main(String[] args) {
        //创建一个baoZi对象
        BaoZi baoZi = new BaoZi();
        //创建给一个生产线程（包子）对象
        ProduceThread produceThread = new ProduceThread(baoZi);
        ConsumeThread consumeThread = new ConsumeThread(baoZi);

        produceThread.start();
        consumeThread.start();
    }
}
