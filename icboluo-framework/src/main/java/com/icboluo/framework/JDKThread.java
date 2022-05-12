package com.icboluo.framework;

import com.icboluo.util.ThreadUtil;

import java.util.PriorityQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author icboluo
 * @since 2022-01-10 15:43
 */
public class JDKThread {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5, 10, 1000L, TimeUnit.SECONDS, new SynchronousQueue<>());
        String str = """
                方法开始
                先定义一个字符串
                然后异步执行一些函数
                紧接着打印定义好的字符串
                """;
        threadPool.execute(() -> new ThreadUtil().sleep5s());
        threadPool.execute(() -> new ThreadUtil().sleep5s());
//         线程池如果不关的话，主服务也不会关的，现象就是控制台一直不结束
        threadPool.shutdown();
        System.out.println(str);
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.add(4);
        queue.add(7);
        queue.add(2);
        queue.add(6);
        queue.add(Integer.valueOf("99999999999999999999999999999"));
        System.out.println(queue);
    }
}
