package com.icboluo.leetcode.after_0200;

import com.icboluo.common.ListNode;

/**
 * @author icboluo
 * @since 2022-10-31 13:11
 */
class N0237_删除链表中的节点 {
    public static void main(String[] args) {
        N0237_删除链表中的节点 cla = new N0237_删除链表中的节点();
        ListNode listNode = new ListNode(4, 5, 1, 9);
        cla.del(listNode, 9);
        listNode.print();
    }

    /**
     * 好奇怪的写法，竟然可以用要删除的节点作为temp，保存数据，正常代码不会这么写
     *
     * @param listNode
     * @param n
     */
    private void del(ListNode listNode, int n) {
        ListNode cur = listNode;
        while (cur != null) {
            if (cur.next == null) {
                // 还是用前驱节点好一点
            } else {
                if (cur.val == n) {
                    // 当前节点的值用下一个节点替代
                    cur.val = cur.next.val;
                    cur.next = cur.next.next;
                }
                cur = cur.next;
            }
        }
    }
}
