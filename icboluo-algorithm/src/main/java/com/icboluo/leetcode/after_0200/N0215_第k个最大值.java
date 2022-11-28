package com.icboluo.leetcode.after_0200;

import java.util.PriorityQueue;

/**
 * @author icboluo
 * @since 2022-11-26 11:35
 */
class N0215_第k个最大值 {
    public int findKthLargest(int[] nums, int k) {
        // pq中升序存储前k个最大值，第一个就是结果
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int num : nums) {
            pq.add(num);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        return pq.poll();
    }
}
