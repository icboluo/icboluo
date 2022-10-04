package com.icboluo.leetcode.fivehundred;

import com.icboluo.common.ListNode;

class N0021_合并2个有序链表 {
    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1, 2, 4);
        ListNode listNode2 = new ListNode(1, 3, 4);
        ListNode listNode = merge1(listNode1, listNode2);
        listNode.print();
    }

    /**
     * 循环/迭代
     *
     * @param listNode1
     * @param listNode2
     * @return
     */
    private static ListNode merge1(ListNode listNode1, ListNode listNode2) {
        ListNode header = new ListNode(0);
        ListNode cur = header;
        while (listNode1 != null && listNode2 != null) {
            if (listNode1.val < listNode2.val) {
                cur.next = new ListNode(listNode1.val);
                listNode1 = listNode1.next;
            } else {
                cur.next = new ListNode(listNode2.val);
                listNode2 = listNode2.next;
            }
            cur = cur.next;
        }
        if (listNode1 != null) {
            cur.next = listNode1;
        } else {
            cur.next = listNode2;
        }
        return header.next;
    }

    /**
     * 递归
     *
     * @param listNode1
     * @param listNode2
     * @return
     */
    private static ListNode merge2(ListNode listNode1, ListNode listNode2) {
        ListNode cur;
        // 如果2个链表为空，返回的是另一个链表的所有数据
        if (listNode1 == null) {
            return listNode2;
        } else if (listNode2 == null) {
            return listNode1;
        }
        // 如果左链表小，cur为左链表，则返回...
        if (listNode1.val < listNode2.val) {
            cur = new ListNode(listNode1.val);
            cur.next = merge2(listNode1.next, listNode2);
        } else {
            cur = new ListNode(listNode2.val);
            cur.next = merge2(listNode1, listNode2.next);
        }
        return cur;
    }
}
