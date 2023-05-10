package com.icboluo.leetcode.linkedlist;

import com.icboluo.common.ListNode;

/**
 * @author icboluo
 * @since 2023-03-02 23:19
 */
class N0725_均分链表 {
    public ListNode[] splitListToParts(ListNode head, int k) {
        int length = head.length();
        int chuShu = length / k;
        int moShu = length % k;
        ListNode[] arr = new ListNode[k];
        int i = 0;
        while (head != null) {
            int len = i < moShu ? chuShu + 1 : chuShu;
            // 用pre追着head指针
            ListNode pre = head;
            for (int j = 0; j < len; j++) {
                if (arr[i] == null) {
                    arr[i] = head;
                }
                pre = head;
                head = head.next;
            }
            pre.next = null;
            i++;
        }
        return arr;
    }
}
