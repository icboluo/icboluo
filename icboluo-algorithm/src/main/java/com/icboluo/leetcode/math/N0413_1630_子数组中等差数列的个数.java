package com.icboluo.leetcode.math;

import java.util.*;
import java.util.stream.IntStream;

/**
 * @author icboluo
 * @since 2023-01-14 19:12
 */
class N0413_1630_子数组中等差数列的个数 {
    public static void main(String[] args) {
        var cla = new N0413_1630_子数组中等差数列的个数();
        System.out.println(cla.checkArithmeticSubarrays1(new int[]{4, 6, 5, 9, 3, 7}, new int[]{0, 0, 2}, new int[]{2, 3, 5}));
    }

    /**
     * 子数组任意2个连续元素差值相等
     * 求的是子数组的个数，可以使用这样的解法 1,2,3,4是合法的，其中123是合法的，所以子数组为1个，234是合法的，所以有2个，总共有3个
     *
     * @param nums
     * @return
     */
    public int numberOfArithmeticSlices(int[] nums) {
        int cur = 0;
        int sum = 0;
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] - nums[i - 1] == nums[i - 1] - nums[i - 2]) {
                cur++;
                // 很好的求连续子数组个数的方法
                sum += cur;
            } else {
                cur = 0;
            }
        }
        return sum;
    }

    // 0446 等差子序列 hard

    /**
     * 1630 等差子数组，后2个数组是范围
     *
     * @param nums
     * @param l
     * @param r
     * @return
     */
    public List<Boolean> checkArithmeticSubarrays1(int[] nums, int[] l, int[] r) {
        List<Boolean> list = new ArrayList<>();
        for (int i = 0; i < l.length; i++) {
            int left = l[i];
            int right = r[i];
            boolean isValid = true;
            // 将数组进行copy，然后选中区域进行排序，查看差值
            int[] clone = nums.clone();
            Arrays.sort(clone, left, right + 1);
            for (int j = left + 2; j <= right; j++) {
                if (clone[j] - clone[j - 1] != clone[j - 1] - clone[j - 2]) {
                    isValid = false;
                    break;
                }
            }
            list.add(isValid);
        }
        return list;
    }

    public List<Boolean> checkArithmeticSubarrays2(int[] nums, int[] l, int[] r) {
        List<Boolean> list = new ArrayList<>();
        for (int i = 0; i < l.length; i++) {
            boolean b = isOk(nums, l[i], r[i]);
            list.add(b);
        }
        return list;
    }

    private boolean isOk(int[] arr, int left, int right) {
        HashSet<Integer> set = new HashSet<>();
        IntSummaryStatistics intSummaryStatistics = IntStream.range(left, right + 1).map(i -> {
            set.add(arr[i]);
            return arr[i];
        }).summaryStatistics();
        int max = intSummaryStatistics.getMax();
        int min = intSummaryStatistics.getMin();
        // 判断等差数列的另一种方法
        int interval = (max - min) / (right - left);
        // 这块是必须的，要不然有的情况通过不了，不知道是为什么
        if ((max - min) % (right - left) != 0) {
            return false;
        }
        for (int i = 0; i <= right - left; i++) {
            if (!set.contains(min + i * interval)) {
                return false;
            }
        }
        return true;
    }
}
