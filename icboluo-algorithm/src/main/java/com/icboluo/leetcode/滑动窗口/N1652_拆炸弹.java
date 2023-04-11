package com.icboluo.leetcode.滑动窗口;

/**
 * @author icboluo
 * @since 2023-02-15 23:35
 */
class N1652_拆炸弹 {
    /**
     * 对循环数组进行解密
     * 后k个元素相加 FIXME ERROR
     *
     * @param code
     * @param k
     * @return
     */
    public int[] decrypt(int[] code, int k) {
        int[] arr = new int[code.length];
        if (k == 0) {
            return arr;
        }
        int l = 0;
        int r = 1;
        if (k < 0) {
            k = -k;
            l = code.length - k;
            r = code.length - k;
        }
        int winSum = 0;
        // 对于可以优先处理的逻辑，抽取处理更加合适
        while (r < k) {
            winSum += code[(r++) % code.length];
        }
        while (l <= code.length) {
            int right = code[(r++) % code.length];
            winSum += right;
            // 中间结果值
            arr[l % code.length] = winSum;
            int left = code[(++l) % code.length];
            winSum -= left;
        }
        return arr;
    }
}
