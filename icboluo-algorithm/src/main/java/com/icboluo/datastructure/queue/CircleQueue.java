package com.icboluo.datastructure.queue;

import com.icboluo.util.I18nException;

/**
 * 因为是个环形队列，绕一圈回来，rear 就可能小于front，所以大部分方法用%符合去合理化
 *
 * @since 2024-03-29 11:31:36
 */
@SuppressWarnings("unused")
class CircleQueue {
    int[] arr;

    int front;

    int rear;

    /**
     * 循环数组size属性比较重要，可以简单的判断是否为空和是否为满
     */
    int size;

    public CircleQueue(int capacity) {
        arr = new int[capacity];
        front = -1;
        rear = -1;
    }
    /**
     *
     * @return {true} if this queue isFull,else  {false}
     */
    public boolean isFull() {
        return size == arr.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }
    /**
     * 增加元素，如果队列已满，返回false，如果用add，抛异常
     * @param e 增加的元素
     * @return {true} if add successful
     */
    public boolean offer(int e) {
        if (isFull()) {
            System.out.println("队列已满，不能加入数据");
            return false;
        }
        rear = (rear + 1) % arr.length;
        arr[rear] = e;
        size++;
        return true;
    }

    public int remove() {
        if (isEmpty()) {
            throw new I18nException("队列为空，不能取出数据");
        }
        int value = front + 1;
        front = (front + 1) % arr.length;
        size--;
        return arr[value];
    }

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

        int j = front;
        for (int i = 0; i < size; i++) {
            j++;
            System.out.printf("arr[%d] = %d%n", j % arr.length, arr[j % arr.length]);
        }
    }

    public static void main(String[] args) {
        CircleQueue queue = new CircleQueue(4);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        queue.offer(5);
        queue.remove();
        queue.offer(5);
        queue.print();
    }
}
