package com.icboluo.leetcode.after_0200;

import com.icboluo.common.ListNode;

/**
 * @author icboluo
 * @since 2022-10-31 13:35
 */
class N0328_奇偶链表 {
    public static void main(String[] args) {
        N0328_奇偶链表 cla = new N0328_奇偶链表();
        ListNode listNode = new ListNode(1, 2, 3, 4, 5);
        ListNode res = cla.m(listNode);
        res.print();
    }

    private ListNode m(ListNode listNode) {
        ListNode ji = new ListNode();
        ListNode ou = new ListNode();
        ListNode jiCur = ji;
        ListNode ouCur = ou;
        ListNode cur = listNode;
        int count = 0;
        while (cur != null) {
            count++;
            if (count % 2 == 0) {
                ouCur.next = cur;
                ouCur = ouCur.next;
            }else {
                jiCur.next = cur;
                jiCur = jiCur.next;
            }
            cur = cur.next;
        }
        ouCur.next = null;
        jiCur.next = ou.next;
        return ji.next;
    }
}
