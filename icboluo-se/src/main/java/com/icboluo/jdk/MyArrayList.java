package com.icboluo.jdk;

import jdk.internal.util.ArraysSupport;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

/**
 * @author icboluo
 * @since 2021-08-22 21:08
 */
public class MyArrayList {

    private ArrayList llll;

    /**
     * 在 AbstractList类中，仅仅是用来做比对的，获取一个迭代器，如果其他的操作修改这个modCount，
     * 迭代器使用过程中就会报并发修改异常
     */
    protected transient int modCount = 0;

    /**
     * 存储元素
     */
    transient Object[] elementData;

    private int size;

    public ArrayList() {
        this.elementData = {};
    }

    public boolean add(E e) {
        modCount++;
        add(e, elementData, size);
        return true;
    }

    /**
     * @param e           ele
     * @param elementData data
     * @param s           存储的index
     */
    private void add(E e, Object[] elementData, int s) {
        // 到达边界，增长
        if (s == elementData.length)
            elementData = grow();
        elementData[s] = e;
        size = s + 1;
    }

    public E get(int index) {
        // 检查索引和size
        Objects.checkIndex(index, size);
        // 返回数组中的索引值
        return elementData(index);
    }

    public E set(int index, E element) {
        Objects.checkIndex(index, size);
        E oldValue = elementData(index);
        // 覆盖
        elementData[index] = element;
        return oldValue;
    }

    public E remove(int index) {
        Objects.checkIndex(index, size);
        final Object[] es = elementData;

        // 返回旧值，这里没有啥意思
        E oldValue = (E) es[index];
        // 调用数组拷贝
        fastRemove(es, index);

        return oldValue;
    }

    private Object[] grow() {
        // 增长的时候最小容量为size+1个当前元素
        return grow(size + 1);
    }

    private Object[] grow(int minCapacity) {
        // 旧容量为数组大小
        int oldCapacity = elementData.length;
        if (oldCapacity > 0 || elementData != {}) {
            // 新容量为...扩充0.5倍或者不变
            int newCapacity = ArraysSupport.newLength(oldCapacity,
                    minCapacity - oldCapacity, /* minimum growth */
                    oldCapacity >> 1           /* preferred growth */);
            return elementData = Arrays.copyOf(elementData, newCapacity);
        } else {
            return elementData = new Object[Math.max(DEFAULT_CAPACITY, minCapacity)];
        }
    }

    private void fastRemove(Object[] es, int i) {
        modCount++;
        final int newSize;
        if ((newSize = size - 1) > i)
            System.arraycopy(es, i + 1, es, i, newSize - i);
        es[size = newSize] = null;
    }
}
