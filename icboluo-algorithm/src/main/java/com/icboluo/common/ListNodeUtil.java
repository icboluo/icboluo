package com.icboluo.common;

/**
 * @author icboluo
 * @since 2023-01-10 21:23
 */
class ListNodeUtil {
    public static void main(String[] args) {
        ListNodeUtil cla = new ListNodeUtil();
        ListNode listNode = new ListNode(1, 2, 3, 4, 5);
        // cla.reverseList1(listNode).print();
        cla.reverseList3(listNode, 2, 4).print();
    }

    /**
     * N0206 反转链表
     * 修改指向反转，和新建链表的代码是相似的
     *
     * @param head
     * @return
     */
    public ListNode reverseList1(ListNode head) {
        ListNode cur = head;
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
     * @param head
     * @return
     */
    public ListNode reverseList2(ListNode head) {
        ListNode cur = head;
        ListNode reverseHead = new ListNode();
        ListNode next;
        while (cur != null) {
            next = cur.next;
            // 将新链表的数据放到当前节点后面
            cur.next = reverseHead.next;
            // 将当前的节点做为头节点
            reverseHead.next = cur;
            cur = next;
        }
        return reverseHead.next;
    }

    /**
     * 区间内反转
     *
     * @param head 1,2,3,4,5
     * @param a    2
     * @param b    4
     * @return 1, 4, 3, 2, 5
     */
    public ListNode reverseList3(ListNode head, int a, int b) {
        ListNode aPre = null;
        ListNode aCur = head;
        for (int i = 0; i < a - 1; i++) {
            aPre = aCur;
            aCur = aCur.next;
        }
        // cur=2
        ListNode bPre = aPre;
        ListNode bCur = aCur;
        ListNode next = null;
        for (int i = 0; i < b - a + 1; i++) {
            next = bCur.next;
            bCur.next = bPre;
            bPre = bCur;
            bCur = next;
        }
        // 这里因为已经到5了，所以应该指向pre节点
        assert aPre != null;
        aPre.next = bPre;
        aCur.next = next;
        return head;
    }
}
