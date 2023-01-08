package com.icboluo.jdk;

/**
 * @author icboluo
 * @since 2021-08-22 21:08
 */
public class MyArrayList {

    /**
     * 在 AbstractList类中，仅仅是用来做比对的，获取一个迭代器，如果其他的操作修改这个modCount，
     * 迭代器使用过程中就会报并发修改异常
     */
    protected transient int modCount = 0;
}
