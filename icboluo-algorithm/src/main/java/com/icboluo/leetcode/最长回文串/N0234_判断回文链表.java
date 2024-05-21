package com.icboluo.leetcode.最长回文串;

import com.icboluo.common.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author icboluo
 * @since 2022-10-31 13:02
 */
class N0234_判断回文链表 {
    public static void main(String[] args) {
        var cla = new N0234_判断回文链表();
        ListNode listNode = new ListNode(1, 2, 2, 1);
        boolean b = cla.isPalindrome1(listNode);
        System.out.println("b = " + b);
    }

    /**
     * 转换成数组进行判断
     *
     * @param listNode
     * @return
     */
    public boolean isPalindrome1(ListNode listNode) {
        // TODO 移动至公共类
        List<Integer> list = new ArrayList<>();
        ListNode cur = listNode;
        while (cur != null) {
            list.add(cur.val);
            cur = cur.next;
        }
        int[] arr = new int[list.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = list.get(i);
        }
        for (int i = 0; i < arr.length / 2; i++) {
            if (arr[i] != arr[arr.length - i - 1]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 链表后半段反转和前半段比较
     * optimize 这道题的难点是单链表难以反向遍历，但是使用后序遍历，问题会变得简单
     *
     * @param listNode
     * @return
     */
    public boolean isPalindrome2(ListNode listNode) {
        ListNode left = listNode;
        ListNode right = listNode;
        // 确认中心点
        while (right != null && right.next != null) {
            left = left.next;
            right = right.next.next;
        }
        ListNode pre = null;
        ListNode cur = left;
        ListNode next;
        // 反转
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        // 比对
        while (pre != null) {
            if (pre.val != listNode.val) {
                return false;
            }
            pre = pre.next;
            listNode = listNode.next;
        }
        return true;
    }
}
