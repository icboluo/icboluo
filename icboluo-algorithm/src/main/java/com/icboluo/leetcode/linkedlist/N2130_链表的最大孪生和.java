package com.icboluo.leetcode.linkedlist;

import com.icboluo.common.ListNode;

/**
 * @author icboluo
 * @since 2022-12-28 23:26
 */
class N2130_链表的最大孪生和 {
    /**
     * 链表首尾相加，依次往内迭代，求最大值；双指针
     *
     * @param head
     * @return
     */
    public int pairSum(ListNode head) {
        int max = Integer.MIN_VALUE;
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        assert slow != null;
        // 后半部分反转，即可next读取元素
        ListNode mid = slow.reverse();
        while (mid != null) {
            int temp = head.val + mid.val;
            max = Math.max(max, temp);
            head = head.next;
            mid = mid.next;
        }
        return max;
    }
}
