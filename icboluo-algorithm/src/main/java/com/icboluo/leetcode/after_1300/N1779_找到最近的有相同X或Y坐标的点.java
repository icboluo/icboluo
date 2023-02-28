package com.icboluo.leetcode.after_1300;

import java.util.Comparator;
import java.util.stream.IntStream;

/**
 * @author icboluo
 * @since 2023-02-28 21:27
 */
class N1779_找到最近的有相同X或Y坐标的点 {
    // 2个点，x或y相同，求距离最小的索引
    public int nearestValidPoint(int x, int y, int[][] points) {
        return IntStream.range(0, points.length)
                .filter(i -> points[i][0] == x || points[i][1] == y)
                .mapToObj(i -> new int[]{Math.abs(x - points[i][0]) + Math.abs(y - points[i][1]), i})
                .sorted(Comparator.comparingInt(a -> a[0]))
                .map(a -> a[1])
                .findFirst()
                .orElse(-1);
    }
}
