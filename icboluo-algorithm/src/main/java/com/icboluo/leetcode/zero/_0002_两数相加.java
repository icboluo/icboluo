package com.icboluo.leetcode.zero;

import com.icboluo.common.ListNode;

/**
 * @author icboluo
 * @date 2020-09-27 20:22
 */
public class _0002_两数相加 {
    public static void main(String[] args) {
        ListNode listNode01 = new ListNode(2, 4, 3);
        ListNode listNode02 = new ListNode(5, 6, 4);
        getTwoNumSum(listNode01, listNode02);
    }

    /**
     * 两个链表从前向后处理，将得到的结果放到新链表的最前面
     *
     * @param listNode01
     * @param listNode02
     * @return
     */
    private static void getTwoNumSum(ListNode listNode01, ListNode listNode02) {
        ListNode header = new ListNode(0);
        header.print();
    }

}
