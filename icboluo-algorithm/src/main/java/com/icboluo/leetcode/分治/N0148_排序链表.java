package com.icboluo.leetcode.分治;

import com.icboluo.common.ListNode;

/**
 * @author icboluo
 * @since 2023-09-10 19:07
 */
class N0148_排序链表 {
    /**
     * 归并排序写起来并没有那么难
     *
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 链表半分
        ListNode pre = null;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        pre.next = null;
        // 2段链表分别合并
        ListNode l1 = sortList(head);
        ListNode l2 = sortList(slow);
        // 合并2个排序链表
        return merge(l1, l2);
    }

    private ListNode merge(ListNode l1, ListNode l2) {
        ListNode head = new ListNode();
        ListNode cur = head;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            }else{
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        if (l1 != null) {
            cur.next = l1;
        }
        if (l2 != null) {
            cur.next = l2;
        }
        return head.next;
    }
}
