package com.icboluo.leetcode.滑动窗口;

/**
 * @author icboluo
 * @since 2023-02-15 23:35
 */
class N1652_拆除炸弹 {
    /**
     * 后k个元素相加 FIXME ERROR
     *
     * @param code
     * @param k
     * @return
     */
    public int[] decrypt(int[] code, int k) {
        int[] arr = new int[code.length];
        int l = 1;
        int r = 1;
        int winSum = 0;
        while (l <= code.length) {
            int right = code[(r++) % code.length];
            winSum += right;
            if (r - l == k) {
                arr[l - 1] = winSum;
                int left = code[l++];
                winSum -= left;
            }
        }
        return arr;
    }
}
