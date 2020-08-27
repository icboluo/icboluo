package com.icboluo.datastructure.queue;

class ArrayQueue {
    /**
     * 队列容量
     */
    private final int maxSize;
    /**
     * 队列头
     */
    private int front;
    /**
     * 队列尾
     */
    private int rear;
    /**
     * 用于存放数据，模拟队列
     */
    private final int[] arr;

    public ArrayQueue(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        //指向队列头的前一个位置
        front = -1;
        //包含队列尾的数据
        rear = -1;
    }

    public boolean ifFull() {
        return rear == maxSize - 1;
    }

    public boolean isEmpty() {
        return rear == front;
    }

    /**
     * 增加元素，如果队列已满，返回false，如果用add，抛异常
     *
     * @param n the element to add
     * @return {true}if the element was added to this queue,else {false}
     *
     */
    public boolean offer(int n) {
        if (ifFull()) {
            System.out.println("队列以满，不能加入数据");
            return false;
        }
        rear++;
        arr[rear] = n;
        return true;
    }

    public int get() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，不能取出数据");
        }
        front++;
        return arr[front];
    }

    public void show() {
        if (isEmpty()) {
            System.out.println("队列为空，不能取出数据");
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n", i, arr[i]);
        }
    }

    /**
     * 显示元素，如果队列为空，抛出异常，如果用peek方法，返回的则是null
     *
     * @return the head of this queue
     */
    public int element() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，没有数据");
        }
        return arr[front + 1];
    }
}
