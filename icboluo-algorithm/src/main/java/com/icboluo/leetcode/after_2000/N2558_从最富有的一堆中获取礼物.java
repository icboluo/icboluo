package com.icboluo.leetcode.after_2000;

import java.util.PriorityQueue;

/**
 * @author icboluo
 * @since 2023-04-21 0:50
 */
class N2558_从最富有的一堆中获取礼物 {
    public long pickGifts(int[] gifts, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for (int gift : gifts) {
            pq.offer(gift);
        }
        for (int i = 0; i < k; i++) {
            Integer poll = pq.poll();
            assert poll != null;
            int target = (int) Math.sqrt(poll);
            pq.offer(target);
        }
        return pq.stream().mapToLong(i -> (long) i).sum();
    }
}
