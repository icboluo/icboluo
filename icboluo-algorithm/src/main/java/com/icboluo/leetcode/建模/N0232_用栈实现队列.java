package com.icboluo.leetcode.建模;

import java.util.Stack;

/**
 * @author icboluo
 * @since 2023-01-14 14:08
 */
class N0232_用栈实现队列 {
    /**
     * 我们使用2个栈，add的时候放入input里面，pop的时候把input里面的全部copy进output里面，这样就可以保证顺序了
     */
    private Stack<Integer> inputStack;
    private Stack<Integer> outputStack;

    public N0232_用栈实现队列() {
        inputStack = new Stack<>();
        outputStack = new Stack<>();
    }

    public void push(int x) {
        inputStack.push(x);
    }

    public int pop() {
        peek();
        return outputStack.pop();
    }

    public int peek() {
        if (outputStack.empty()) {
            while (!inputStack.empty()) {
                outputStack.push(inputStack.pop());
            }
        }
        return outputStack.peek();
    }

    public boolean empty() {
        return inputStack.empty() && outputStack.empty();
    }
}
