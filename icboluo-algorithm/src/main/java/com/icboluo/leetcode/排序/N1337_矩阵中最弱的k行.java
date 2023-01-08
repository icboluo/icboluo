package com.icboluo.leetcode.排序;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * @author icboluo
 * @since 2023-01-08 0:35
 */
class N1337_矩阵中最弱的k行 {
    /**
     * 矩阵和排序
     *
     * @param mat
     * @param k
     * @return
     */
    public int[] kWeakestRows(int[][] mat, int k) {
        return IntStream.range(0, mat.length).boxed().sorted((a, b) -> {
            int aSum = Arrays.stream(mat[a]).sum();
            int bSum = Arrays.stream(mat[b]).sum();
            return aSum - bSum;
        }).limit(k).mapToInt(Integer::intValue).toArray();
    }
}
