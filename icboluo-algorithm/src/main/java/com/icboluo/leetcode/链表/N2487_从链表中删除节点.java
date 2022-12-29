package com.icboluo.leetcode.链表;

import com.icboluo.common.ListNode;

/**
 * @author icboluo
 * @since 2022-12-30 0:14
 */
class N2487_从链表中删除节点 {
    /**
     * 删除右侧有更大节点的节点
     * 使链表递减，递归的方式，重新整理链表，这个代码非常巧妙
     *
     * @param head
     * @return
     */
    public ListNode removeNodes(ListNode head) {
        if (head == null) {
            return null;
        }
        head.next = removeNodes(head.next);
        if (head.next != null && head.val < head.next.val) {
            return head.next;
        }
        return head;
    }
}
