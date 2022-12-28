package com.icboluo.leetcode.链表;

import com.icboluo.common.ListNode;

/**
 * @author icboluo
 * @since 2022-12-28 23:23
 */
 class N2095_删除链表的中间节点 {
    public ListNode deleteMiddle(ListNode head) {
        if (head.next == null) {
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (true) {
            fast = fast.next.next;
            if (fast == null || fast.next == null) {
                slow.next = slow.next.next;
                break;
            }else{
                slow = slow.next;
            }
        }
        return head;
    }
}
