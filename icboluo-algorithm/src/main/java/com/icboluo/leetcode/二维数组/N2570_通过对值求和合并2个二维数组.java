package com.icboluo.leetcode.二维数组;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author icboluo
 * @since 2023-04-21 1:00
 */
class N2570_通过对值求和合并2个二维数组 {
    // 排序有问题 FIXME ERROR
    public int[][] mergeArrays(int[][] nums1, int[][] nums2) {
        Map<Integer, Integer> map = Stream.concat(Arrays.stream(nums1), Arrays.stream(nums2))
                .collect(Collectors.toMap(arr -> arr[0], arr -> arr[1], Integer::sum));
        int[][] res = new int[map.size()][2];
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            res[i][0] = entry.getKey();
            res[i][1] = entry.getValue();
            i++;
        }
        return res;
    }
}
