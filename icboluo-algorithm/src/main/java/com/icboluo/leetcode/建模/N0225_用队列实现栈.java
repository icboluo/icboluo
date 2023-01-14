package com.icboluo.leetcode.建模;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author icboluo
 * @since 2023-01-14 14:03
 */
class N0225_用队列实现栈 {
    private Queue<Integer> queue;

    public N0225_用队列实现栈() {
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
}
