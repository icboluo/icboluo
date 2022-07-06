package com.icboluo.leetcode.双指针;

import com.icboluo.common.ListNode;

/**
 * @author icboluo
 * @since 2021-11-18 23:07
 */
public class N0019_链表倒数第N个节点 {
    public static void main(String[] args) {
        ListNode listNode = new ListNode(1, 2);
        N0019_链表倒数第N个节点 cla = new N0019_链表倒数第N个节点();
        ListNode ans = cla.removeNthFromEnd2(listNode, 1);
        ans.print();
    }

    public ListNode removeNthFromEnd1(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        int length = length(head);
        if (length == n) {
            return head.next;
        }
        ListNode cur = head;
        for (int i = 0; i < length - n - 1; i++) {
            cur = cur.next;
        }
        if (cur.next != null) {
            cur.next = cur.next.next;
        }
        return head;
    }

    private int length(ListNode listNode) {
        int length = 0;
        while (listNode != null) {
            length++;
            listNode = listNode.next;
        }
        return length;
    }

    public ListNode removeNthFromEnd2(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        ListNode fake = new ListNode(0);
        fake.next = head;
        ListNode left = fake;
        ListNode right = fake;
        while (right != null) {
            if (n >= 0) {
                right = right.next;
                n--;
            } else {
                left = left.next;
                right = right.next;
            }
        }
        left.next = left.next.next;
        return fake.next;
    }
}
