package com.icboluo.leetcode.after_1000;

/**
 * @author icboluo
 * @since 2022-11-26 18:00
 */
class N1037_有效的三角形 {
    public boolean isBoomerang(int[][] points) {
        int[] a = points[0];
        int[] b = points[1];
        int[] c = points[2];
        // 2个斜率，这样写会除0，是不合理的
//        double ab = (a[1] - b[1]) / (a[0] - b[0]);
//        double ac = (a[1] - c[1]) / (a[0] - c[0]);
        return (a[1] - b[1]) * (a[0] - c[0]) != (a[0] - b[0]) * (a[1] - c[1]);
    }
}
