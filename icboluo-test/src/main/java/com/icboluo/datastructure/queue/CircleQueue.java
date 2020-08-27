package com.icboluo.datastructure.queue;

/**
 * 因为是个环形队列，绕一圈回来，rear 就可能小于front，所以大部分方法用%符合去合理化
 */
class CircleQueue {
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

    public CircleQueue(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        //指向队列的第一个元素
        front = 0;
        //向队列的最后一个元素的后一个位置
        rear = 0;
    }

    /**
     * 用real-front+1==maxSize 来判断，没有考虑当环形队列front大于real的情况
     *
     * @return {true} if this queue isFull,else  {false}
     */
    public boolean ifFull() {
        return (rear + 1) % maxSize == front;
    }

    public boolean isEmpty() {
        return rear == front;
    }

    /**
     * 增加元素，如果队列已满，返回false，如果用add，抛异常
     * @param n 增加的元素
     * @return {true} if add successful
     */
    public boolean offer(int n) {
        if (ifFull()) {
            System.out.println("队列以满，不能加入数据");
            return false;
        }
        //本身real就指向最后的后一个位置，直接添加元素即可，不过加完之后，指针要想后移一位
        arr[rear] = n;
        rear = (rear + 1) % maxSize;
        return true;
    }

    public int get() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，不能取出数据");
        }
        //把front对应的值放到temp，将front后移，将temp返回
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    public void show() {
        if (isEmpty()) {
            System.out.println("队列为空，不能取出数据");
        }
        //从front开始遍历，遍历多少个元素
        for (int i = front; i < front + size(); i++) {
            //%d代表元素,%maxSize永远不会超过maxSize
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    //显示元素，如果队列为空，抛出异常，如果用peek方法，返回的则是null
    public int element() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，没有数据");
        }
        return arr[front];
    }

    //有效元素的个数
    public int size() {
        //for example: rear=1 front=0 maxSize=3->size=1
        return (rear + maxSize - front) % maxSize;

    }
}