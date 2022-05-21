package com.icboluo.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>线程创建的三种方式
 * <p>通过 Executor 来启动线程比使用 Thread 的 start 方法更好，除了更易管理，效率更好（用线程池实现，节约开销）外，还有关键的一点：有助于避免 this 逃逸问题。
 *
 * @author icboluo
 * @since 2022-05-21 12:56
 */
public class ThreadCreate {

    public static void main(String[] args) throws Exception {
        Run run = new Run();
        Call call = new Call();
        Thr thr = new Thr();

        new Thread(run).start();
        ExecutorService es = Executors.newCachedThreadPool();
        es.submit(call);
        thr.start();
        // 线程池如果不关的话，主服务也不会关的，现象就是控制台一直不结束，其实是守护线程
        es.shutdown();
    }
}
