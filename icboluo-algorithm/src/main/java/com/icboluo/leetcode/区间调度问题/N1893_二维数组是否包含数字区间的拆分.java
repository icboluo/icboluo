package com.icboluo.leetcode.区间调度问题;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author icboluo
 * @since 2023-03-24 0:04
 */
class N1893_二维数组是否包含数字区间的拆分 {
    /**
     * 二维数组大于数字区间
     * 虽然我们可以建立一个数组包含left=right的所有值，再使用前缀和数组判断是否均大于0，但是不可取
     *
     * @param ranges
     * @param left
     * @param right
     * @return
     */
    public boolean isCovered(int[][] ranges, int left, int right) {
        List<int[]> list = Arrays.stream(ranges).sorted(Comparator.comparingInt(a -> a[0])).toList();
        for (int[] arr : list) {
            // 保证区间连接
            if (arr[0] > left) {
                return false;
            }
            // 顺延left区间
            left = Math.max(left, arr[1] + 1);
        }
        // 如果最终left区间被延长到比right都大，说明区间内均有值
        return left > right;
    }
}
