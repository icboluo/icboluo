package com.icboluo.common;

import java.util.Stack;

/**
 * 单链表节点，为了做算法简单，只用节点做单链表，并不进行方法抽取
 *
 * @author icboluo
 * @since 2020-09-23 20:57
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                '}';
    }

    public void print() {
        ListNode cur = this;
        ListNode next = cur.next;
        System.out.print("cur->" + cur.val);
        while (next != null) {
            System.out.print("->" + next.val);
            next = next.next;
        }
        System.out.println();
    }

    public ListNode(int... arr) {
        ListNode head = new ListNode(0);
        ListNode pre = head;
        for (int j : arr) {
            pre.next = new ListNode(j);
            pre = pre.next;
        }
        this.val = head.next.val;
        this.next = head.next.next;
    }

    public ListNode end() {
        ListNode cur = this;
        while (cur.next != null) {
            cur = cur.next;
        }
        return cur;
    }

    public void reversePrint(ListNode listNode) {
        if (listNode == null) {
            return;
        }
        reversePrint(listNode);
        // 后序遍历拥有了前面的返回值
        System.out.println(listNode.val);
    }

    /**
     * 修改指向反转，和新建链表的代码是相似的
     *
     * @return 反转后的链表
     */
    public ListNode reverse1() {
        ListNode cur = this;
        ListNode pre = null;
        ListNode next;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    /**
     * 新建链表，每次将旧链表的元素放到新链表的最前面
     *
     * @return 反转后的链表
     */
    public ListNode reverse2() {
        ListNode cur = this;
        ListNode reverseHead = new ListNode();
        ListNode next;
        while (cur != null) {
            next = cur.next;
//            将新链表的数据放到当前节点后面
            cur.next = reverseHead.next;
//           将当前的节点做为头节点
            reverseHead.next = cur;
            cur = next;
        }
        return reverseHead.next;
    }

    /**
     * 1,2,3,4,5
     *
     * @param a 2
     * @param b 4
     * @return 1, 4, 3, 2, 5
     */
    public ListNode reverse3(int a, int b) {
        ListNode head = this;
        ListNode aPre = null;
        ListNode aCur = this;
        for (int i = 0; i < a - 1; i++) {
            aPre = aCur;
            aCur = aCur.next;
        }
//        cur=2
        ListNode bPre = aPre;
        ListNode bCur = aCur;
        ListNode next = null;
        for (int i = 0; i < b - a + 1; i++) {
            next = bCur.next;
            bCur.next = bPre;
            bPre = bCur;
            bCur = next;
        }
//        这里因为已经到5了，所以应该指向pre节点
        aPre.next = bPre;
        aCur.next = next;
        return head;
    }

    public int length() {
        ListNode cur = this;
        int length = 0;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        return length;
    }

    /**
     * <p></>1.将链表反转，然后打印
     * <p></>2.遍历链表，将链表中的元素存到stack中，打印
     */
    public void reversePrint() {
        ListNode cur = this;
        Stack<ListNode> stack = new Stack<>();
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        System.out.print("cur ");
        while (!stack.isEmpty()) {
            System.out.print("->" + stack.pop());
        }
        System.out.println();
    }
}
