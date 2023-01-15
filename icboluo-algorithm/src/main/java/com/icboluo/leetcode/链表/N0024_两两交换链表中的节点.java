package com.icboluo.leetcode.链表;

import com.icboluo.common.ListNode;

/**
 * @author icboluo
 * @since 2023-01-15 16:31
 */
class N0024_两两交换链表中的节点 {
    public static void main(String[] args) {
        var cla = new N0024_两两交换链表中的节点();
        ListNode listNode = new ListNode(1, 2, 3, 4);
        ListNode res = cla.swapPairs1(listNode);
    }

    /**
     * 要求：必须交换节点，不能只交换值
     * 迭代
     * 3个指针，指针一次移动2个位置
     *
     * @param head
     * @return
     */
    public ListNode swapPairs1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = new ListNode();
        newHead.next = head.next;
        ListNode cur = newHead;
        while (head != null && head.next != null) {
            ListNode first = head;
            ListNode second = head.next;

            // swap
            first.next = second.next;
            second.next = first;
            // 新链表指向，并移动新链表
            cur.next = second;
            cur = first;

            head = first.next;
        }
        return newHead.next;
    }

    /**
     * 递归
     * 双指针，second.next=first
     *
     * @param head
     * @return
     */
    public ListNode swapPairs2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode first = head;
        ListNode second = head.next;

        first.next = swapPairs2(second.next);
        second.next = first;
        return second;
    }
}
