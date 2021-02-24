package com.icboluo.leetcode.animation.zero;

import com.icboluo.common.ListNode;

/**
 * @author icboluo
 * @date 2020-10-09 20:57
 */
public class _0023_合并K个排序链表 {
    public static void main(String[] args) {
        ListNode listNode01 = new ListNode(1, 4, 5);
        ListNode listNode02 = new ListNode(1, 3, 4);
        ListNode listNode03 = new ListNode(2, 6);
        ListNode listNode = m1(listNode01, listNode02, listNode03);
    }

    /**
     * todo 最小堆
     *
     * @param listNode01
     * @param listNode02
     * @param listNode03
     * @return
     */
    private static ListNode m1(ListNode listNode01, ListNode listNode02, ListNode listNode03) {
        return null;
    }

    /**
     * 归并+分治
     * 先把链表分解成一半、再一半、再一半、再两个一合并（或者一个一合并）
     *
     * @param listNode01
     * @param listNode02
     * @param listNode03
     * @return
     */
    private static ListNode m2(ListNode listNode01, ListNode listNode02, ListNode listNode03) {
        return null;
    }
}
