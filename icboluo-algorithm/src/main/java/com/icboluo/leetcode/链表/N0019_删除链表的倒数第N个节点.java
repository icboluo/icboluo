package com.icboluo.leetcode.链表;

import com.icboluo.common.ListNode;

/**
 * @author icboluo
 * @since 2020-10-09 20:39
 */
class N0019_删除链表的倒数第N个节点 {
    public static void main(String[] args) {
        ListNode listNode = new ListNode(1, 2, 3, 4, 5);
        N0019_删除链表的倒数第N个节点 cla = new N0019_删除链表的倒数第N个节点();
        cla.removeNthFromEnd1(listNode, 2).print();
    }

    /**
     * todo 双指针 使双指针的距离为n，移动右指针到最后即可
     *
     * @param listNode
     * @param n
     * @return
     */
    private static ListNode m2(ListNode listNode, int n) {
        return null;
    }

    /**
     * 倒数第n个就是length-n个，先求长度在一个一个迭代
     *
     * @param head
     * @param n
     * @return
     */
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
