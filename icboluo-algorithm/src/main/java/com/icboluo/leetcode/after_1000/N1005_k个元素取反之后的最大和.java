package com.icboluo.leetcode.after_1000;

import java.util.PriorityQueue;

/**
 * @author icboluo
 * @since 2022-12-05 23:44
 */
class N1005_k个元素取反之后的最大和 {
    public int largestSumAfterKNegations(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int num : nums) {
            pq.add(num);
        }
        for (int i = 0; i < k; i++) {
            Integer poll = pq.poll();
            assert poll != null;
            pq.add(-poll);
        }
        return pq.stream().mapToInt(Integer::intValue).sum();
    }
}
