package com.icboluo.leetcode.linkedlist;

import com.icboluo.common.ListNode;

/**
 * @author icboluo
 * @since 2022-12-28 23:20
 */
class N0876_链表的中间节点 {
    public ListNode middleNode(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
}
