package com.icboluo.datastructure.stack;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * N0225_单队列实现栈
 *
 * @since 2023-01-14 14:03
 */
class QueueStackSingle {
    /**
     * 用队列的头做栈顶
     */
    Queue<Integer> queue;

    public QueueStackSingle() {
        this.queue = new LinkedList<>();
    }

    public void push(int x) {
        // 这个方法直接转换数据类型
        queue.add(x);
        // 对于n-1个数添加到末尾
        for (int i = 0; i < queue.size() - 1; i++) {
            queue.add(queue.remove());
        }
    }

    public int pop() {
        return queue.remove();
    }

    public int top() {
        return queue.peek();
    }

    public boolean empty() {
        return queue.isEmpty();
    }

    public void print() {
        AtomicInteger i = new AtomicInteger();
        queue.forEach(val -> System.out.printf("stack[%d] = %d%n", i.getAndIncrement(), val));
    }
}
