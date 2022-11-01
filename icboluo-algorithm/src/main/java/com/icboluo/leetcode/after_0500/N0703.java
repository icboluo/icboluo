package com.icboluo.leetcode.after_0500;

import java.util.PriorityQueue;

/**
 * @author icboluo
 * @since 2021-05-10 13:05
 */
 class N0703 {

    PriorityQueue<Integer> queue;
    //    因为add函数需要idx，所以idx作为成员变量0
    int idx;

    public N0703(int k, int[] nums) {
//        指定大小
        queue = new PriorityQueue<>(k);
        idx = k;
        for (int num : nums) {
//            调新增函数
            add(num);
        }
    }

    /**
     * 2 4 5 8
     * 2 3 4 5 8
     * 2 3 4 5 5 8
     * 2 3 4 5 5 8 10
     * 2 3 4 5 5 8 9 10
     * 2 3 4 4 5 5 8 9 10
     * @param val
     * @return
     */
    public int add(int val) {
        if (queue.size() < idx) {
            queue.offer(val);
        } else if (queue.peek() < val) {
            queue.poll();
            queue.offer(val);

        }
        return queue.peek();
    }
}
