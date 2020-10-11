package com.icboluo.leetcode.zero;

import com.icboluo.common.ListNode;

/**
 * @author icboluo
 * @date 2020-10-09 21:22
 */
public class _0025_K个一组翻转链表 {
    public static void main(String[] args) {
        ListNode listNode = new ListNode(1, 2, 3, 4, 5);
        ListNode res = m1(listNode, 2);
    }

    /**
     * 迭代
     * 做法和递归一样，while 翻转前n个，每次重置pre和end节点
     * <p>
     * 翻转链表的解法:
     * 1.双指针，start end交换
     * 2.遍历每一个节点到新链表的最前面
     * 两种解法代码上是相似（几乎一样）的
     *
     * @param listNode
     * @param n
     * @return
     */
    private static ListNode m1(ListNode listNode, int n) {
        return null;
    }

    /**
     * 递归
     * start end 进行翻转，start.next=递归交换后的节点
     *
     * @param listNode
     * @param n
     * @return
     */
    private static ListNode m2(ListNode listNode, int n) {
        return null;
    }
}
