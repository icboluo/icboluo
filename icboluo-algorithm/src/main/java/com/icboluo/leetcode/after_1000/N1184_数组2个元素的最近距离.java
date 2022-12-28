package com.icboluo.leetcode.after_1000;

/**
 * @author icboluo
 * @since 2022-12-28 20:53
 */
class N1184_数组2个元素的最近距离 {
    /**
     * 因为可以走2条路，求2条路的最小值即可
     *
     * @param distance    车站之间的距离
     * @param start       出发地
     * @param destination 目的地
     * @return
     */
    public int distanceBetweenBusStops(int[] distance, int start, int destination) {
        // 处理start大于destination的异常情况
        if (start > destination) {
            int temp = start;
            start = destination;
            destination = temp;
        }
        int a = 0;
        int b = 0;
        for (int i = 0; i < start; i++) {
            a += distance[i];
        }
        for (int i = start; i < destination; i++) {
            b += distance[i];
        }
        for (int i = destination; i < distance.length; i++) {
            a += distance[i];
        }
        return Math.min(a, b);
    }
}
