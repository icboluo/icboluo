package com.icboluo.leetcode.linkedlist;

import com.icboluo.common.ListNode;
import org.springframework.lang.NonNull;

/**
 * @author icboluo
 * @since 2020-10-09 20:39
 */
class N0019_删除链表的倒数第N个节点 {
    public static void main(String[] args) {
        ListNode listNode = new ListNode(1, 2, 3, 4, 5);
        var cla = new N0019_删除链表的倒数第N个节点();
        cla.removeNthFromEnd1(listNode, 2).print();
    }

    /**
     * 倒数第n个就是length-n个，先求长度在一个一个迭代
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd1(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        int length = length(head);
        if (length == n) {
            return head.next;
        }
        ListNode cur = head;
        for (int i = 0; i < length - n - 1; i++) {
            cur = cur.next;
        }
        if (cur.next != null) {
            cur.next = cur.next.next;
        }
        return head;
    }

    private int length(ListNode listNode) {
        int length = 0;
        while (listNode != null) {
            length++;
            listNode = listNode.next;
        }
        return length;
    }

    public ListNode removeNthFromEnd2(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        ListNode fake = new ListNode(0);
        fake.next = head;
        ListNode left = fake;
        ListNode right = fake;
        while (right != null) {
            // 循环拆成2部分，先移动右节点，再2个节点一起移动，代码比较简单
            // 删除链表的倒数第n个节点，首先要找到链表的倒数第n+1个节点
            if (n >= 0) {
                right = right.next;
                n--;
            } else {
                left = left.next;
                right = right.next;
            }
        }
        left.next = left.next.next;
        return fake.next;
    }

    public ListNode removeNthFromEnd3(@NonNull ListNode head, int n) {
        int len = length(head, n);
        if (len == n) {
            return head.next;
        }
        return head;
    }

    private int length(ListNode node, int n) {
        if (node == null) {
            return 0;
        }
        // 强大的后序遍历
        int len = length(node.next, n) + 1;
        // 倒数第n+1个节点
        if (len == n + 1) {
            node.next = node.next.next;
        }
        return len;
    }
}
