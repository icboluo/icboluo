package com.icboluo.leetcode.前缀和_差分数组;

/**
 * @author icboluo
 * @since 2023-10-21 20:32
 */
class N0370_区间加法_差分数组 {
    public int[] corpFlightBookings(int[][] bookings, int n) {
        DifferenceArr differenceArr = new DifferenceArr(new int[n]);
        for (int[] booking : bookings) {
            differenceArr.increment(booking[0] - 1, booking[1] - 1, booking[2]);
        }
        return differenceArr.restore();
    }
}
