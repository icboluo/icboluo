package com.icboluo.common;

/**
 * 单链表节点，为了做算法简单，只用节点做单链表，并不进行方法抽取
 *
 * @author icboluo
 * @date 2020-09-23 20:57
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

    public ListNode() {
        this.val = 0;
        this.next = null;
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
        ListNode header = new ListNode(0);
        ListNode pre = header;
        for (int j : arr) {
            pre.next = new ListNode(j);
            pre = pre.next;
        }
        this.val = header.next.val;
        this.next = header.next.next;
    }

    public ListNode end() {
        ListNode cur = this;
        while (cur.next != null) {
            cur = cur.next;
        }
        return cur;
    }

    public ListNode reverse() {
        ListNode cur = this;
        ListNode pre = new ListNode();
        ListNode next;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
