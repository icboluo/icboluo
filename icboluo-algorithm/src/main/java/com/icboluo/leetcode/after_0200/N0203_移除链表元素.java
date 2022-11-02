package com.icboluo.leetcode.after_0200;

import com.icboluo.common.ListNode;

/**
 * @author icboluo
 * @since 2022-10-10 22:02
 */
class N0203_移除链表元素 {
    public static void main(String[] args) {
        N0203_移除链表元素 cla = new N0203_移除链表元素();
        ListNode listNode = new ListNode(1, 2, 6, 3, 4, 5, 6);
        cla.m1(listNode, 6);
        listNode.print();
    }

    private void m1(ListNode listNode, int n) {
        ListNode cur = listNode;
        while (cur.next != null) {
            if (cur.next.val == n) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
    }

    /**
     * 递归逆向删
     *
     * @param listNode
     * @param n
     */
    private ListNode m2(ListNode listNode, int n) {
// 递归到链表末尾
        if (listNode == null) {
            return null;
        }
        // pre节点的next域指向下次递归的return
        listNode.next = m2(listNode.next, n);
        // 相当于把next域的指向重新指定了一下
        return listNode.val == n ? listNode.next : listNode;
    }
}
