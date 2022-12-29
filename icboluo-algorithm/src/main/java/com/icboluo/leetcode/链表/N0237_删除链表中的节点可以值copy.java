package com.icboluo.leetcode.链表;

import com.icboluo.common.ListNode;

/**
 * @author icboluo
 * @since 2022-12-30 0:50
 */
 class N0237_删除链表中的节点可以值copy {
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
