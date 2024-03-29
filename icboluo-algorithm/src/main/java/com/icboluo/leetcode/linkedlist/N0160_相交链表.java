package com.icboluo.leetcode.linkedlist;

import com.icboluo.common.ListNode;

class N0160_相交链表 {
    public static void main(String[] args) {
        ListNode listNode4 = new ListNode(4);
        ListNode listNode50 = new ListNode(5, 0);
        ListNode listNode836 = new ListNode(8, 3, 6);
        ListNode listNode1 = new ListNode(1);
        listNode4.next = listNode1;
        listNode50.next.next = listNode1;
        listNode1.next = listNode836;
        N0160_相交链表 cla = new N0160_相交链表();
        cla.getIntersectionNode(listNode4, listNode50);
    }

    /**
     * 双指针，a链表后面跟b，b链表后面跟a，两个指针相遇的地方就是链表相交的地方
     *
     * @param listNode01
     * @param listNode02
     */
    public ListNode getIntersectionNode(ListNode listNode01, ListNode listNode02) {
        if (listNode01 == null || listNode02 == null) {
            return null;
        }
        ListNode aNode = listNode01;
        ListNode bNode = listNode02;
        // 这里是内存值比较
        while (aNode != bNode) {
            if (aNode != null) {
                aNode = aNode.next;
            } else {
                aNode = listNode02;
            }
            if (bNode != null) {
                bNode = bNode.next;
            } else {
                bNode = listNode01;
            }
        }
        return aNode;
    }
}
