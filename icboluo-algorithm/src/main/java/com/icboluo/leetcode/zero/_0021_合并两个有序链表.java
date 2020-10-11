package com.icboluo.leetcode.zero;

import com.icboluo.common.ListNode;

/**
 * @author icboluo
 * @date 2020-10-09 20:48
 */
public class _0021_合并两个有序链表 {
    public static void main(String[] args) {
        ListNode listNode01 = new ListNode(1, 2, 4);
        ListNode listNode02 = new ListNode(1, 3, 4);
        ListNode res = m1(listNode01, listNode02);
    }

    /**
     * 循环
     * 新链表依次拼接两个链表中的元素，直到一个链表全为空，将另一个链表全部拼接到这个链表后面
     *
     * @param listNode01
     * @param listNode02
     * @return
     */
    private static ListNode m1(ListNode listNode01, ListNode listNode02) {
        return null;
    }

    /**
     * 递归
     * 令cur为较小的元素，next为合并后的链表
     *
     * @param listNode01
     * @param listNode02
     * @return
     */
    private static ListNode m2(ListNode listNode01, ListNode listNode02) {
        return null;
    }
}
