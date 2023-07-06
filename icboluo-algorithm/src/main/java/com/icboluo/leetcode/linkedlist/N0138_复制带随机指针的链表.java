package com.icboluo.leetcode.linkedlist;

/**
 * @author icboluo
 * @since 2023-07-07 4:39
 */
class N0138_复制带随机指针的链表 {
    // 深拷贝，链表的深拷贝均可以使用这个方式，非常的简单，但是其实2种的时间复杂度是一致的
    // 方法1：为每一个节点新建一个map，这样的话额外的空间复杂度为O(n)，要使用地址值作为链表的id
    // 方法2：自我复制，脱落
    public Node copyRandomList(Node head) {
        if (head == null) {
            return head;
        }
        // 第一步，链表变长2倍，使变成这样的格式1-1`-2-2`
        Node cur = head;
        while (cur != null) {
            Node next = cur.next;
            cur.next = new Node(cur.val);
            cur.next.next = next;
            cur = next;
        }
        // 第二步，对随机指针
        cur = head;
        while (cur != null) {
            if (cur.random != null) {
                cur.next.random = cur.random.next;
            }
            cur = cur.next;
        }

        // 第三步：分离cur和copy
        cur = head;
        Node copyHead = head.next;
        Node copy = copyHead;
        while (copy.next != null) {
            cur.next = cur.next.next;
            cur = cur.next;

            copy.next = copy.next.next;
            copy = copy.next;
        }
        // 最后一个再置空
        cur.next = cur.next.next;
        return copyHead;
    }

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
