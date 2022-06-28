package com.icboluo.leetcode.双指针;

import com.icboluo.common.ListNode;

/**
 * @author icboluo
 * @since 2022-06-29 0:21
 */
public class N0083_删除排序链表中的重复元素 {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null) {
            if (slow.val != fast.val) {
                slow.next = fast;
                slow = slow.next;
            }
            fast = fast.next;
        }
        // 这块是为了防止最后面的元素有相同的
        slow.next = null;
        return head;
    }
}
