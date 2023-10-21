package com.icboluo.leetcode.前缀和_差分数组;

/**
 * @author icboluo
 * @since 2023-10-21 20:32
 */
class N0370_区间加法 {
    public int[] corpFlightBookings(int[][] bookings, int n) {
        DifferenceArr differenceArr = new DifferenceArr(new int[n]);
        for (int[] booking : bookings) {
            differenceArr.increment(booking[0] - 1, booking[1] - 1, booking[2]);
        }
        return differenceArr.restore();
    }

    /**
     * 370 区间加法 差分数组
     *
     * @author l30024701
     * @since 2023-10-14 17:47:46
     */
    public class DifferenceArr {
        int[] diff;

        public DifferenceArr(int[] arr) {
            diff = new int[arr.length];
            diff[0] = arr[0];
            for (int i = 1; i < diff.length; i++) {
                diff[i] = arr[i] - arr[i - 1];
            }
        }

        public int[] restore() {
            int[] res = new int[diff.length];
            res[0] = diff[0];
            for (int i = 1; i < res.length; i++) {
                res[i] = res[i - 1] + diff[i];
            }
            return res;
        }

        public void increment(int i, int j, int val) {
            diff[i] += val;
            // 当增加的区间到最后的时候，就不用再减少了
            if (j + 1 < diff.length) {
                diff[j + 1] -= val;
            }
        }
    }
}
