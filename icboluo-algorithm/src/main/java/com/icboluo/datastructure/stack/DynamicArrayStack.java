package com.icboluo.datastructure.stack;

import com.icboluo.util.I18nException;

import java.util.ArrayList;
import java.util.List;

/**
 * 动态数组实现栈
 *
 * @since 2024-03-29 9:51:57
 */
@SuppressWarnings("unused")
class DynamicArrayStack {
    List<Integer> stack;

    public DynamicArrayStack() {
        stack = new ArrayList<>();
    }

    public void push(int value) {
        stack.add(value);
    }

    public int pop() {
        if (isEmpty()) {
            throw new I18nException("栈空");
        }
        return stack.remove(stack.size() - 1);
    }

    public int peek() {
        if (isEmpty()) {
            throw new I18nException("栈空");
        }
        return stack.get(stack.size() - 1);
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public void print() {
        for (int i = stack.size() - 1; i >= 0; i--) {
            System.out.printf("stack[%d] = %d%n", i, stack.get(i));
        }
    }

    public static void main(String[] args) {
        DynamicArrayStack stack = new DynamicArrayStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.print();
    }
}
