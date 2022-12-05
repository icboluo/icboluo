package com.icboluo.leetcode.after_0800;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author icboluo
 * @since 2022-12-05 21:33
 */
class N0888_公平糖果交换 {
    public int[] fairCandySwap(int[] aliceSizes, int[] bobSizes) {
        int sum1 = Arrays.stream(aliceSizes).sum();
        int sum2 = Arrays.stream(bobSizes).sum();
        // 假设a比较大
        int cha = sum1 - sum2;
        Arrays.sort(aliceSizes);
        Arrays.sort(bobSizes);
        Set<Integer> set2 = Arrays.stream(bobSizes).boxed().collect(Collectors.toSet());
        for (int aliceSize : aliceSizes) {
            int need = aliceSize - cha / 2;
            if (set2.contains(need)) {
                return new int[]{aliceSize, need};
            }
        }
        return null;
    }
}
