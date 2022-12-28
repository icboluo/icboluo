package com.icboluo.leetcode.after_1000;

/**
 * @author icboluo
 * @since 2022-12-28 21:59
 */
class N1266_参观所有点的时间 {
    /***
     * 因为能走对角线，其实问题就变得简单了，那个坐标差距更大，以那个为主即可
     * @param points
     * @return
     */
    public int minTimeToVisitAllPoints(int[][] points) {
        int sum = 0;
        int a = points[0][0];
        int b = points[0][1];
        for (int i = 0; i < points.length; i++) {
            sum += Math.max(Math.abs(a - points[i][0]), Math.abs(b - points[i][1]));
            a = points[i][0];
            b = points[i][1];
        }
        return sum;
    }
}
