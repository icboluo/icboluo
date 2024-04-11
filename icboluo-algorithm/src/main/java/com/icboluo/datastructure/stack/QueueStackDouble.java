package com.icboluo.datastructure.stack;

import com.icboluo.util.I18nException;

import java.util.LinkedList;
import java.util.Queue;

/**
 * N0225_双队列实现栈
 * 栈本身是有相反的性质的，先进后出，所以，用栈实现队列需要双栈即可，时间复杂度为O（1）
 * 但是队列实现栈比较麻烦，因为队列的先进先出性质，保证了无论怎么操作，队列的顺序是无法改变的；所以只能在取数据的时候留一个元素，采取这种做法，时间复杂度O（n）
 *
 * @since 2024-03-29 10:21:50
 */
class QueueStackDouble {
    /**
     * 保存栈内元素
     */
    Queue<Integer> queue1;

    Queue<Integer> queue2;

    /**
     * 保存栈顶元素，并非指针
     */
    int top;

    public QueueStackDouble() {
        this.queue1 = new LinkedList<>();
        this.queue2 = new LinkedList<>();
    }

    public void push(int value) {
        queue1.add(value);
        top = value;
    }

    public int pop() {
        if (isEmpty()) {
            throw new I18nException("栈空");
        }
        // 将队列的元素放出队列，直至剩最后一个，队列尾的元素就是刚进队列的元素
        while (queue1.size() > 1) {
            // 请注意，操作到这里的时候，top元素已经被更新了
            top = queue1.remove();
            queue2.add(top);
        }
        // 将最后一个元素移除之后，队列为空
        int tempTop = queue1.remove();
        Queue<Integer> tempQuery = queue1;
        queue1 = queue2;
        queue2 = tempQuery;
        return tempTop;
    }

    public int peek() {
        if (isEmpty()) {
            throw new I18nException("栈空");
        }
        return top;
    }

    public boolean isEmpty() {
        return queue1.isEmpty();
    }

    public static void main(String[] args) {
        QueueStackDouble stack = new QueueStackDouble();
        stack.push(1);
        stack.push(2);
        stack.push(3);
    }
}
