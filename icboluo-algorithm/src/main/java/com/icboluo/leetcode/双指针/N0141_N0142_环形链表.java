package com.icboluo.leetcode.双指针;

import com.icboluo.common.ListNode;

/**
 * @author icboluo
 * @since 2022-07-07 0:35
 */
class N0141_N0142_环形链表 {
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null) {
            slow = slow.next;
            if (fast.next == null) {
                return false;
            }
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }
}
