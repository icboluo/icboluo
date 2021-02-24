package com.icboluo.leetcode.animation.zero;

import com.icboluo.common.ListNode;

/**
 * @author icboluo
 * @date 2020-09-27 20:22
 */
public class _0002_两数相加 {
    public static void main(String[] args) {
        ListNode listNode01 = new ListNode(2, 4, 3);
        ListNode listNode02 = new ListNode(5, 6, 4);
        ListNode listNode = getTwoNumSum(listNode01, listNode02);
        listNode.print();
    }

    /**
     * 两个链表从前向后处理，将得到的结果放到新链表的最前面
     *
     * @param listNode01
     * @param listNode02
     * @return
     */
    private static ListNode getTwoNumSum(ListNode listNode01, ListNode listNode02) {
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

}
