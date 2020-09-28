package com.icboluo.common;

/**
 * 单链表节点，为了做算法简单，只用节点做单链表，并不进行方法抽取
 *
 * @author icboluo
 * @date 2020-09-23 20:57
 */
public class ListNode {
    int val;
    ListNode next;

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
        ListNode header = new ListNode(0);
        ListNode pre = header;
        for (int i = 0; i < arr.length; i++) {
            ListNode cur = new ListNode(arr[i]);
            pre.next = cur;
            pre = pre.next;
        }
        this.val = header.next.val;
        this.next = header.next.next;
    }
}
