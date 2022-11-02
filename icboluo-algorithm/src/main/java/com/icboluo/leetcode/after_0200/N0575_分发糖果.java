package com.icboluo.leetcode.after_0200;

import java.util.Arrays;

/**
 * @author icboluo
 * @since 2022-11-02 12:57
 */
class N0575_分发糖果 {
    public int distributeCandies(int[] candyType) {
        int limit = candyType.length / 2;
        long count = Arrays.stream(candyType).distinct().count();
        return Math.min(limit, (int) count);
    }
}
