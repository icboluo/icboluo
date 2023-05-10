package com.icboluo.leetcode.linkedlist;

import com.icboluo.common.ListNode;
import com.icboluo.common.ListNodeUtil;

/**
 * @author icboluo
 * @since 2020-10-09 21:22
 */
class N0025_K个一组翻转链表 {
    public static void main(String[] args) {
        var cla = new N0025_K个一组翻转链表();
        ListNode listNode = new ListNode(1, 2, 3, 4, 5);
        ListNode res = cla.reverseKGroup1(listNode, 2);
        res.print();
    }

    /**
     * 递归
     * start end 进行翻转，start.next=递归交换后的节点
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup1(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode start = head;
        ListNode end = head;
        for (int i = 0; i < k; i++) {
            if (end == null) {
                return head;
            }
            end = end.next;
        }
        // 将本段链表反转，本段链表的开始节点就变成了最终节点
        ListNode reverse = ListNodeUtil.reverse1(start, end);
        // 本段链表的最终节点的下一个节点，应该是下一段链表反转之后的头节点
        start.next = reverseKGroup1(end, k);
        return reverse;
    }

    /**
     * 迭代
     * 做法和递归一样，while 翻转前n个，每次重置pre和end节点
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup2(ListNode head, int k) {
        ListNode header = new ListNode();
        header.next = head;
        ListNode end = header;

        ListNode pre = header;
        while (end.next != null) {
            for (int i = 0; i < k; i++) {
                if (end != null) {
                    end = end.next;
                } else {
                    break;
                }
            }
            if (end == null) {
                break;
            }
            // 当前组的开始节点
            ListNode start = pre.next;
            // 当前组的下一个节点
            ListNode next = end.next;

            // 前驱节点的下一个节点，是将本段链表进行反转之后的值
            pre.next = ListNodeUtil.reverse1(start, next);
            // 当前组最后一个节点的下一个节点，应该和下一组连接
            start.next = next;
            pre = start;
            end = pre;
        }
        return header.next;
    }
}
