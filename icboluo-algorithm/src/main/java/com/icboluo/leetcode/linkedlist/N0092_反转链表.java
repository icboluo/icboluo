package com.icboluo.leetcode.linkedlist;

import com.icboluo.common.ListNode;

/**
 * @author icboluo
 * @since 2023-10-21 20:42
 */
class N0092_反转链表 {
    // 反转链表，并且返回反转后的头节点
    public ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 将链表分成2部分，头和其他；反转链表就是反转其他，然后反转头和其他的关系就可以了
        ListNode next = head.next;
        ListNode two = reverse(next);
        next.next = head;
        head.next = null;
        return two;
    }

    // 后继节点
    ListNode after = null;

    // 反转前n个节点
    public ListNode reverseN(ListNode head, int n) {
        if (n == 1) {
            after = head.next;
            return head;
        }
        ListNode next = head.next;
        ListNode two = reverseN(next, n - 1);
        next.next = head;
        head.next = after;
        return two;
    }

    // 反转区间结果
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (m == 1) {
            return reverseN(head, n);
        }
        head.next = reverseBetween(head.next, m - 1, n - 1);
        return head;
    }

    ListNode left = null;

    // 是否是回文链表
    public boolean isPalindrome(ListNode head) {
        left = head;
        return traverse(head);
    }

    public boolean traverse(ListNode right) {
        if (right == null) {
            return true;
        }
        boolean res = traverse(right.next);
        res = res && (left.val == right.val);
        // 因为是后序遍历，所以left指针最后移动，相反，right指针最先移动到最后边，相当于链表从两边开始遍历
        left = left.next;
        return res;
    }
}
