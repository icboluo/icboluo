package com.icboluo.leetcode.去掉一部分之后的平均值;

import java.util.Arrays;

/**
 * @author icboluo
 * @since 2023-02-15 22:49
 */
class N1619_删除一些元素后的数组均值 {
    // 最小的5%和最大的5%
    public double trimMean(int[] arr) {
        Arrays.sort(arr);
        return Arrays.stream(arr, arr.length / 20, arr.length - arr.length / 20)
                .average().getAsDouble();
    }
}
