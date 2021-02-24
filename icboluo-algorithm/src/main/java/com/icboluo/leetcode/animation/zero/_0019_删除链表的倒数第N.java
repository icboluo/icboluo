package com.icboluo.leetcode.animation.zero;

import com.icboluo.common.ListNode;

/**
 * @author icboluo
 * @date 2020-10-09 20:39
 */
public class _0019_删除链表的倒数第N {
    public static void main(String[] args) {
        ListNode listNode = new ListNode(1, 2, 3, 4, 5);
        ListNode res = m1(listNode, 2);
        listNode.print();
    }

    /**
     * 倒数第n个就是length-n个，先求长度在一个一个迭代
     *
     * @param listNode
     * @param n
     * @return
     */
    private static ListNode m1(ListNode listNode, int n) {
        int length = listNode.length();
        ListNode pre = listNode;
        for (int i = 0; i < length - n-1; i++) {
            pre = pre.next;
        }
        pre.next = pre.next.next;
        return listNode;
    }

    /**
     * todo 双指针 使双指针的距离为n，移动右指针到最后即可
     *
     * @param listNode
     * @param n
     * @return
     */
    private static ListNode m2(ListNode listNode, int n) {
        return null;
    }
}
