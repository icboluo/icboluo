package com.icboluo.leetcode.链表;

import com.icboluo.common.ListNode;

/**
 * @author icboluo
 * @since 2022-12-28 23:46
 */
class N1721_交换链表中的节点 {
    /**
     * 交换链表的第n个和倒数第n个节点的值；题目仅仅要求交换节点的值，不要求交换节点，交换节点是困难的
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode swapNodes(ListNode head, int k) {
        ListNode fast = head;
        for (int i = 0; i < k - 1; i++) {
            fast = fast.next;
        }
        ListNode left = fast;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        int temp = left.val;
        left.val = slow.val;
        slow.val = temp;
        return head;
    }
}
