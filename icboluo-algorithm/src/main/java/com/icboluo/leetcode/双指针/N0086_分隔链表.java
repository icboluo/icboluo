package com.icboluo.leetcode.双指针;

import com.icboluo.common.ListNode;

/**
 * @author icboluo
 * @since 2022-07-07 0:39
 */
class N0086_分隔链表 {

    public ListNode partition(ListNode head, int x) {
        ListNode left = new ListNode(0);
        ListNode leftHead = left;
        ListNode right = new ListNode(0);
        ListNode rightHead = right;
        while (head != null) {
            if (head.val >= x) {
                right.next = head;
                right = right.next;
            } else {
                left.next = head;
                left = left.next;
            }
            head = head.next;
        }
        left.next = rightHead.next;
        right.next = null;
        return leftHead.next;
    }

    public static void main(String[] args) {
        N0086_分隔链表 cla = new N0086_分隔链表();
        ListNode listNode = new ListNode(1, 4, 3, 2, 5, 2);
        ListNode partition = cla.partition(listNode, 3);
        partition.print();
    }
}
