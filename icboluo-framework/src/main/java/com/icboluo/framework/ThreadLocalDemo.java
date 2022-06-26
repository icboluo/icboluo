package com.icboluo.framework;

/**
 * @author icboluo
 * @since 2022-06-26 17:07
 */
public class ThreadLocalDemo {
    public static void main(String[] args) {
        ThreadLocal<Integer> threadLocal1 = new ThreadLocal<>();
        ThreadLocal<Integer> threadLocal2 = new ThreadLocal<>();
        // 断点访问
        threadLocal1.set(1);
        threadLocal2.set(2);
        Integer integer = threadLocal1.get();
        System.out.println("integer = " + integer);
    }
}
