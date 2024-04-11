package com.icboluo.datastructure.stack;

import com.icboluo.util.I18nException;

/**
 * 数组实现栈
 * 将栈中的其他方法迁移出去，将 HeroNode 中 nickName 删除
 * 各个操作都是常数时间开销
 * 每隔一段时间进行的倍数操作的时间开销较大
 * <p>
 * 链表栈
 * 各个操作都是常数时间开销
 * 栈规模的增加和减少都很容易
 * 每个操作都需要使用额外的空间和时间开销来处理指针
 * <p>
 * 双端栈 也称为双端队列
 *
 * @see java.util.Stack
 * @see java.util.Deque
 */
@SuppressWarnings("unused")
class ArrayStack {
    int[] stack;

    /**
     * 栈顶索引
     */
    int top = -1;

    public ArrayStack(int size) {
        stack = new int[size];
    }

    public void push(int value) {
        // is full
        if (top == stack.length - 1) {
            throw new I18nException("栈满");
        }
        top++;
        stack[top] = value;
    }

    public int pop() {
        if (isEmpty()) {
            throw new I18nException("栈空");
        }
        int value = stack[top];
        top--;
        return value;
    }

    public int peek() {
        if (isEmpty()) {
            throw new I18nException("栈空");
        }
        return stack[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public void print() {
        if (isEmpty()) {
            System.out.println("栈空");
        }
        for (int i = top; i >= 0; i--) {
            // %d 数字， %n 换行
            System.out.printf("stack[%d] = %d%n", i, stack[i]);
        }
    }

    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(10);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.print();
    }
}
