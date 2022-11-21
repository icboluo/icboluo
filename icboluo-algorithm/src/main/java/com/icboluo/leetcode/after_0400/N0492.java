package com.icboluo.leetcode.after_0400;

/**
 * @author icboluo
 * @since 2022-11-02 12:39
 */
class N0492 {
    public int[] constructRectangle(int area) {
        int sqrt = (int) Math.sqrt(area);
        while (area % sqrt != 0) {
            sqrt--;
        }
        return new int[]{sqrt, area / sqrt};
    }
}
