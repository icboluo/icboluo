package com.icboluo.common;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 单链表节点，为了做算法简单，只用节点做单链表，并不进行方法抽取
 *
 * @author icboluo
 * @since 2020-09-23 20:57
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                '}';
    }

    public void print() {
        ListNode cur = this;
        ListNode next = cur.next;
        System.out.print("cur->" + cur.val);
        while (next != null) {
            System.out.print("->" + next.val);
            next = next.next;
        }
        System.out.println();
    }

    public ListNode(int... arr) {
        if (arr == null) {
            return;
        }
        ListNode head = new ListNode(0);
        ListNode pre = head;
        for (int j : arr) {
            pre.next = new ListNode(j);
            pre = pre.next;
        }
        this.val = head.next.val;
        this.next = head.next.next;
    }

    public ListNode end() {
        ListNode cur = this;
        while (cur.next != null) {
            cur = cur.next;
        }
        return cur;
    }

    public void reversePrint(ListNode listNode) {
        if (listNode == null) {
            return;
        }
        reversePrint(listNode);
        // 后序遍历拥有了前面的返回值
        System.out.println(listNode.val);
    }

    /**
     * 修改指向反转，和新建链表的代码是相似的
     *
     * @return 反转后的链表
     */
    public ListNode reverse() {
        return new ListNodeUtil().reverseList1(this);
    }

    public int length() {
        ListNode cur = this;
        int length = 0;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        return length;
    }

    /**
     * <p></>1.将链表反转，然后打印
     * <p></>2.遍历链表，将链表中的元素存到stack中，打印
     */
    public void reversePrint() {
        ListNode cur = this;
        Deque<ListNode> stack = new ArrayDeque<>();
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        System.out.print("cur ");
        while (!stack.isEmpty()) {
            System.out.print("->" + stack.pop());
        }
        System.out.println();
    }
}
