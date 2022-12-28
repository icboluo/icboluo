package com.icboluo.leetcode.after_1000;

import com.icboluo.common.ListNode;

/**
 * @author icboluo
 * @since 2022-12-28 20:28
 */
class N1290_二进制链表转10进制 {
    public int getDecimalValue(ListNode head) {
        String str = "";
        while (head != null) {
            str += head.val;
            head = head.next;
        }
        return Integer.parseInt(str, 2);
    }
}
