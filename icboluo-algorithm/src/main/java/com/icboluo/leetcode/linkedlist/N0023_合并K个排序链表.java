package com.icboluo.leetcode.linkedlist;

import com.icboluo.common.ListNode;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author icboluo
 * @since 2020-10-09 20:57
 */
class N0023_合并K个排序链表 {
    public static void main(String[] args) {
        var cla = new N0023_合并K个排序链表();
        ListNode listNode01 = new ListNode(1, 4, 5);
        ListNode listNode02 = new ListNode(1, 3, 4);
        ListNode listNode03 = new ListNode(2, 6);
        cla.mergeKLists1(Arrays.asList(listNode01,listNode02,listNode03).toArray(ListNode[]::new)).print();
    }

    // 堆解决
    public ListNode mergeKLists1(ListNode[] lists) {
        ListNode head = new ListNode(0);
        ListNode cur = head;
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> a.val - b.val);
        for (ListNode oneHead : lists) {
            if (oneHead != null) {
                pq.add(oneHead);
            }
        }

        while (!pq.isEmpty()) {
            ListNode poll = pq.poll();
            cur.next = poll;
            if (poll.next != null) {
                pq.add(poll.next);
            }
            cur = cur.next;
        }
        return head.next;
    }

    /**
     * 归并+分治
     * 先把链表分解成一半、再一半、再一半、再两个一合并（或者一个一合并）
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        if (lists.length == 1) {
            return lists[0];
        }
        if (lists.length == 2) {
            return mergeTwo(lists[0], lists[1]);
        }
        // 把链表分解成一半，两两合并
        int mid = lists.length / 2;
        ListNode[] arr1 = new ListNode[mid];
        ListNode[] arr2 = new ListNode[lists.length - mid];
        int count2 = 0;
        for (int i = 0; i < lists.length; i++) {
            if (mid > i) {
                arr1[i] = lists[i];
            } else {
                arr2[count2++] = lists[i];
            }
        }
        // 上面的数组拆分可以使用这个api
//        ListNode[] arr1 = Arrays.copyOfRange(lists, 0, mid);
//        ListNode[] arr2 = Arrays.copyOfRange(lists, 0, mid);
        return mergeTwo(mergeKLists(arr1), mergeKLists(arr2));
    }

    private ListNode mergeTwo(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        ListNode cur;
        if (list1.val < list2.val) {
            cur = new ListNode(list1.val);
            cur.next = mergeTwo(list1.next, list2);
        } else {
            cur = new ListNode(list2.val);
            cur.next = mergeTwo(list1, list2.next);
        }
        return cur;
    }
}
