package com.icboluo.leetcode.前缀和_差分数组;

/**
 * @author icboluo
 * @since 2024-03-26 0:06
 */
class N1109_公司航班系统_差分数组 {
    public int[] corpFlightBookings(int[][] bookings, int n) {
        DifferenceArr differenceArr = new DifferenceArr(new int[n]);
        for (int[] booking : bookings) {
            differenceArr.increment(booking[0] - 1, booking[1] - 1, booking[2]);
        }
        return differenceArr.restore();
    }
}
