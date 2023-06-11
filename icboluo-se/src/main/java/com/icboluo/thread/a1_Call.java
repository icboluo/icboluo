package com.icboluo.thread;

import com.icboluo.util.ThreadUtil;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 通过Callable和Future创建线程
 *
 * @author icboluo
 * @since 2022-05-21 13:27
 */
class a1_Call implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        ThreadUtil.sleep5s();
        return 1;
    }

    private void demo() throws ExecutionException, InterruptedException {
        // 创建Callable接口的实现类，并实现call()方法，该call()方法将作为线程执行体，并且有返回值
        a1_Call call = new a1_Call();
        // 创建Callable实现类的实例，使用FutureTask类来包装Callable对象，该FutureTask对象封装了该Callable对象的call()方法的返回值
        FutureTask<Integer> futureTask = new FutureTask<>(call);
        // 使用FutureTask对象作为Thread对象的target创建并启动新线程。
        // 调用FutureTask对象的get()方法来获得子线程执行结束后的返回值
        Integer integer = futureTask.get();
        // Future可以监控线程运行的状态
        System.out.println(futureTask.isDone());
        System.out.println(integer);
    }
}
