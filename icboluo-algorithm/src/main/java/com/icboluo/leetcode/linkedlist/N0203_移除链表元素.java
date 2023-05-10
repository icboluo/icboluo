package com.icboluo.leetcode.linkedlist;

import com.icboluo.common.ListNode;

/**
 * @author icboluo
 * @since 2022-10-10 22:02
 */
class N0203_移除链表元素 {
    public static void main(String[] args) {
        var cla = new N0203_移除链表元素();
        ListNode listNode = new ListNode(1, 2, 6, 3, 4, 5, 6);
        cla.removeElements1(listNode, 6);
        listNode.print();
    }

    // 要注意删光的情况
    public ListNode removeElements1(ListNode listNode, int n) {
        ListNode head = new ListNode();
        head.next = listNode;
        ListNode cur = head;
        while (cur.next != null) {
            if (cur.next.val == n) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return head.next;
    }

    /**
     * 递归逆向删
     *
     * @param listNode
     * @param n
     */
    public ListNode removeElements2(ListNode listNode, int n) {
        // 递归到链表末尾
        if (listNode == null) {
            return null;
        }
        // pre节点的next域指向下次递归的return
        listNode.next = removeElements2(listNode.next, n);
        // 相当于把next域的指向重新指定了一下
        return listNode.val == n ? listNode.next : listNode;
    }
}
