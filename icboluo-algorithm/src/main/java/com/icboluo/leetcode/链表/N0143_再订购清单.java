package com.icboluo.leetcode.链表;

import com.icboluo.common.ListNode;

/**
 * @author icboluo
 * @since 2022-12-28 23:36
 */
class N0143_再订购清单 {
    /**
     * 链表首一个尾一个重新排序
     *
     * @param head
     */
    public void reorderList(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode right = slow.reverse();
        ListNode left = head;
        while (right != null) {
            // 这个想起来比较抽象，简单来做把每一个的next都标识起来即可
            ListNode leftNext = left.next;
            ListNode rightNext = right.next;

            if (rightNext != null) {
                left.next = right;
                right.next = leftNext;
                left = leftNext;
            }
            right = rightNext;
        }
    }
}
