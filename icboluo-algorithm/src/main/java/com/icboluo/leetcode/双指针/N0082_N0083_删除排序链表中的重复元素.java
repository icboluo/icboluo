package com.icboluo.leetcode.双指针;

import com.icboluo.common.ListNode;

/**
 * @author icboluo
 * @since 2022-06-29 0:21
 */
class N0082_N0083_删除排序链表中的重复元素 {

    /**
     * N0082 只要重复全部删除
     *
     * @param head 头节点
     * @return 删除后的链表
     */
    public ListNode deleteDuplicates1(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode fakeHead = new ListNode(0);
        fakeHead.next = head;
        ListNode pre = fakeHead;
        ListNode cur = head;
        // 1,2,3,3,4,4,5
        while (cur != null) {
            // 前后相等，一直后移
            while (cur.next != null && cur.val == cur.next.val) {
                cur = cur.next;
            }
            // 当前节点没有移动（也可以使用bool flag 来替换，说明当前节点唯一
            if (pre.next == cur) {
                pre = pre.next;
            } else {
                pre.next = cur.next;
            }
            cur = cur.next;
        }
        return fakeHead.next;
    }

    /**
     * N0083 保留一个元素
     *
     * @param head 头节点
     * @return 删除后的链表
     */
    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null) {
            if (slow.val != fast.val) {
                // 先保留一个元素
                slow.next = fast;
                slow = slow.next;
            }
            fast = fast.next;
        }
        // 这块是为了防止最后面的元素有相同的
        slow.next = null;
        return head;
    }

    public static void main(String[] args) {
        N0082_N0083_删除排序链表中的重复元素 cla = new N0082_N0083_删除排序链表中的重复元素();
        ListNode listNode = cla.deleteDuplicates1(new ListNode(1, 2, 3, 3, 4, 4, 5));
        listNode.print();
    }
}
