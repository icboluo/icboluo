package com.icboluo.leetcode.low;

/**
 * @author icboluo
 * @since 2023-06-05 23:26
 */
class N0812_最大三角形面积 {
    public double largestTriangleArea(int[][] points) {
        double max = 0;
        for (int i = 0; i < points.length - 2; i++) {
            for (int j = i + 1; j < points.length - 1; j++) {
                for (int k = j + 1; k < points.length; k++) {
                    max = Math.max(max, area(points[i], points[j], points[k]));
                }
            }
        }
        return max;
    }

    // 证明略
    public double area(int[] a, int[] b, int[] c) {
        return Math.abs(a[0] * (b[1] - c[1]) + b[0] * (c[1] - a[1]) + c[0] * (a[1] - b[1])) / 2.0;
    }
}
