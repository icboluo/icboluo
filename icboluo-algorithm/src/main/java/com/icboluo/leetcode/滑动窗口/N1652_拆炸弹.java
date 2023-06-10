package com.icboluo.leetcode.滑动窗口;

import java.util.Arrays;

/**
 * @author icboluo
 * @since 2023-02-15 23:35
 */
class N1652_拆炸弹 {
    public static void main(String[] args) {
        var cla = new N1652_拆炸弹();
        System.out.println(Arrays.toString(cla.decrypt(new int[]{2, 4, 9, 3}, -2)));
    }

    /**
     * 对循环数组进行解密
     * 后k个元素相加
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
        int l = 1;
        int r = k;
        // 对于负值的处理是困难的
        if (k < 0) {
            l = code.length + k;
            r = code.length - 1;
        }
        int winSum = 0;
        // 对于可以优先处理的逻辑，抽取处理更加合适
        for (int i = l; i <= r; i++) {
            winSum += code[i];
        }
        // 这块不由左右指针的变化而变化，应该默认从0开始，清勿使方法复杂化
        for (int i = 0; i < code.length; i++) {
            arr[i] = winSum;
            winSum += code[(++r) % code.length];
            winSum -= code[(l++) % code.length];
        }
        return arr;
    }
}
