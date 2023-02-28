package com.icboluo.leetcode.两数之和;

import com.icboluo.common.ListNode;

/**
 * @author icboluo
 * @since 2021-11-18 22:56
 */
class N0002_链表2数和 {
    public static void main(String[] args) {
        ListNode listNode01 = new ListNode(2, 4, 3);
        ListNode listNode02 = new ListNode(5, 6, 4);
        var cla = new N0002_链表2数和();
        ListNode listNode = cla.getTwoNumSum(listNode01, listNode02);
        listNode.print();
    }

    /**
     * 两个链表从前向后处理，将得到的结果放到新链表的最前面
     *
     * @param listNode01
     * @param listNode02
     * @return
     */
    public ListNode getTwoNumSum(ListNode listNode01, ListNode listNode02) {
        ListNode head = new ListNode();
        ListNode cur = head;
//        位数
        int i = 0;
        while (listNode01 != null || listNode02 != null) {
            int val1 = listNode01 == null ? 0 : listNode01.val;
            int val2 = listNode02 == null ? 0 : listNode02.val;
            cur.next = new ListNode(i + (val1 + val2) % 10);
            listNode01 = listNode01 == null ? null : listNode01.next;
            listNode02 = listNode02 == null ? null : listNode02.next;
            cur = cur.next;
            i = (val1 + val2) / 10;
        }
        if (i > 0) {
            cur.next = new ListNode(i);
        }
        return head.next;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int w = 0;
        ListNode root = new ListNode();
        ListNode cur = root;
        while (l1 != null || l2 != null) {
            int l1Val = l1 == null ? 0 : l1.val;
            int l2Val = l2 == null ? 0 : l2.val;
            int total = l1Val + l2Val + w;
            cur.next = new ListNode(total % 10);
            w = total / 10;
            cur = cur.next;
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }
        if (w != 0) {
            cur.next = new ListNode(w);
        }
        return root.next;
    }
}
