package com.icboluo.leetcode.math;

/**
 * @author icboluo
 * @since 2022-11-02 12:39
 */
class N0492_构造矩形 {
    /**
     * 求矩形的长宽最小距离，长和宽不相等
     *
     * @param area 面积
     * @return
     */
    public int[] constructRectangle(int area) {
        int sqrt = (int) Math.sqrt(area);
        while (area % sqrt != 0) {
            sqrt--;
        }
        return new int[]{area / sqrt, sqrt};
    }
}
