package com.icboluo.leetcode.after_1000;

import java.util.PriorityQueue;

/**
 * @author icboluo
 * @since 2022-11-26 18:03
 */
class N1046_最后石头的重量 {
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for (int stone : stones) {
            pq.offer(stone);
        }
        while (pq.size() > 1) {
            // 最大的两块拿出来碰
            Integer poll1 = pq.poll();
            Integer poll2 = pq.poll();
            assert poll2 != null;
            // 碰完之后放进去（0也放进去
            pq.offer(Math.abs(poll1 - poll2));
        }
        return pq.poll();
    }
}
