package com.icboluo.leetcode.堆;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * @author icboluo
 * @since 2023-06-05 21:28
 */
class N0295_数据流中的中位数 {
    /**
     * 数字从大到小，所以是 小顶堆
     */
    PriorityQueue<Integer> small = new PriorityQueue<>(Collections.reverseOrder());

    PriorityQueue<Integer> large = new PriorityQueue<>();

    boolean isEven = true;

    /**
     * 代码采取从方案是2个堆,求大顶堆和小顶堆的顶端的avg即可
     */
    public N0295_数据流中的中位数() {
    }

    public void addNum(int num) {
        // 如果初始状态，我们需要加入数据到小顶堆中
        if (isEven) {
            large.offer(num);
            small.offer(large.poll());
        } else {
            small.offer(num);
            large.offer(small.poll());
        }
        isEven = !isEven;
    }

    public double findMedian() {
        if (isEven) {
            return (large.peek() + small.peek()) / 2.0;
        } else {
            return small.peek();
        }
    }
}
