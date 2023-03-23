package com.icboluo.leetcode.元素出现次数;

import java.util.Arrays;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author icboluo
 * @since 2023-03-24 0:24
 */
class N1725_能组成最大正方形的长方形个数 {
    /**
     * 矩形数组，以最小边切割为正方形，求最大正方形count
     *
     * @param rectangles
     * @return
     */
    public int countGoodRectangles(int[][] rectangles) {
        TreeMap<Integer, Integer> eleCountMap = Arrays.stream(rectangles)
                .map(arr -> Math.min(arr[0], arr[1]))
                .collect(Collectors.toMap(Function.identity(), ele -> 1, Integer::sum, TreeMap::new));
        return eleCountMap.lastEntry().getValue();
    }
}
