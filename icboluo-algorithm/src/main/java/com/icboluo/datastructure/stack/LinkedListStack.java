package com.icboluo.datastructure.stack;

import com.icboluo.common.ListNode;
import com.icboluo.util.I18nException;

/**
 * 基于链表实现栈
 *
 * @since 2024-03-29 9:58:14
 */
@SuppressWarnings("unused")
class LinkedListStack {
    ListNode top;

    public void push(int value) {
        ListNode node = new ListNode(value);
        if (isEmpty()) {
            top = node;
        } else {
            top.next = node;
            top = top.next;
        }
    }

    public int pop() {
        if (isEmpty()) {
            throw new I18nException("栈空");
        }
        int val = top.val;
        top = top.next;
        return val;
    }

    public int peek() {
        if (isEmpty()) {
            throw new I18nException("栈空");
        }
        return top.val;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public void print() {
        ListNode head = top;
        int i = 0;
        while (top != null) {
            System.out.printf("stack[%d] = %d%n", i, top.val);
            top = top.next;
            i++;
        }
        top = head;
    }

    public static void main(String[] args) {
        LinkedListStack stack = new LinkedListStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.print();
    }
}
