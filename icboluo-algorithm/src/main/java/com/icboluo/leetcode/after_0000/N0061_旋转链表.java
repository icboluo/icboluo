package com.icboluo.leetcode.after_0000;

import com.icboluo.common.ListNode;

/**
 * @author icboluo
 * @since 2022-11-07 22:15
 */
class N0061_旋转链表 {
    public static void main(String[] args) {
        N0061_旋转链表 cla = new N0061_旋转链表();
        ListNode listNode = new ListNode(1, 2, 3, 4, 5);
        ListNode ans = cla.rotateRight(listNode, 2);
        ans.print();
    }

    // 指针移动，确定几个关键指针即可
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        // 假节点
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode weiZhiZhen = dummy;
        int length = 0;
        while (weiZhiZhen.next != null) {
            length++;
            weiZhiZhen = weiZhiZhen.next;
        }
        ListNode mid = dummy;
        for (int i = 0; i < length - k % length; i++) {
            mid = mid.next;
        }
        weiZhiZhen.next = dummy.next;
        dummy.next = mid.next;
        mid.next = null;
        return dummy.next;
    }
}
