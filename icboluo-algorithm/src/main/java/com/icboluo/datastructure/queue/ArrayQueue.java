package com.icboluo.datastructure.queue;

import com.icboluo.util.I18nException;

/**
 * 一般情况下，我们希望调用api少的报错，所以会多调用 poll和peek，少调用remove和element
 *
 * @since 2024-03-29 11:00:04
 */
@SuppressWarnings("unused")
class ArrayQueue {
    /**
     * 队列添加元素是按照从左往右添加的
     */
    int[] arr;

    /**
     * 头指针，移除元素是移除队列的头部元素
     */
    int front;

    /**
     * 尾指针，添加元素是加到队列的尾部；所以尾指针总是在头指针的前面
     */
    int rear;

    public ArrayQueue(int capacity) {
        arr = new int[capacity];
        front = -1;
        rear = -1;
    }

    public boolean isFull() {
        return rear == arr.length - 1;
    }

    public boolean isEmpty() {
        return front == rear;
    }

    /**
     * 增加元素，如果队列已满，返回false，如果用add，抛异常
     *
     * @param e the element to add
     * @return {true}if the element was added to this queue,else {false}
     */
    public boolean offer(int e) {
        if (isFull()) {
            System.out.println("队列已满，不能加入数据");
            return false;
        }
        rear++;
        arr[rear] = e;
        return true;
    }

    public int remove() {
        if (isEmpty()) {
            throw new I18nException("队列为空，不能取出数据");
        }
        front++;
        return arr[front];
    }

    /**
     * 显示元素，如果队列为空，抛出异常，如果用peek方法，返回的则是null
     *
     * @return the head of this queue
     */
    public int element() {
        if (isEmpty()) {
            throw new I18nException("队列为空，没有数据");
        }
        return arr[front + 1];
    }

    public void print() {
        if (isEmpty()) {
            System.out.println("队列为空");
        }
        for (int i = front + 1; i <= rear; i++) {
            System.out.printf("arr[%d] = %d%n", i, arr[i]);
        }
    }

    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(4);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        queue.offer(5);
        queue.remove();
        queue.print();
    }
}
