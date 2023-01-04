package com.icboluo.leetcode.after_1000;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author icboluo
 * @since 2022-12-28 20:02
 */
class N1200_1984_2144_最小绝对差 {
    public static void main(String[] args) {
        N1200_1984_2144_最小绝对差 cla = new N1200_1984_2144_最小绝对差();
        List<List<Integer>> res = cla.minimumAbsDifference(new int[]{4, 2, 1, 3});
        System.out.println("res = " + res);
    }

    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        Arrays.sort(arr);
        int min = Integer.MAX_VALUE;
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < arr.length - 1; i++) {
            List<Integer> temp = new ArrayList<>();
            if (arr[i + 1] - arr[i] < min) {
                list.clear();
                temp.add(arr[i]);
                temp.add(arr[i + 1]);
                list.add(temp);
                min = arr[i + 1] - arr[i];
            } else if (arr[i + 1] - arr[i] == min) {
                temp.add(arr[i]);
                temp.add(arr[i + 1]);
                list.add(temp);
            }
        }
        return list;
    }

    /**
     * 2144 以折扣价购买糖果的最低成本
     * 买2个糖果可以送一个更便宜的；这就要求这两个糖果的价值接近，然后取较小值
     * 这些题和561是一起的，均属于极小极大值（随便命名的
     *
     * @param cost
     * @return
     */
    public int minimumCost(int[] cost) {
        Arrays.sort(cost);
        int sum = 0;
        for (int i = cost.length - 1; i >= 0; i -= 3) {
            int a = cost[i];
            int b = i == 0 ? 0 : cost[i - 1];
            sum += a + b;
        }
        return sum;
    }

    /**
     * 1984 k分数组，求每组最大值与最小值的最小差 FIXME ERROR
     * TODO 2155 二进制数组中得分最高的所有分区，好多相关问题没有做
     *
     * @param nums
     * @param k
     * @return
     */
    public int minimumDifference1(int[] nums, int k) {
        Arrays.sort(nums);
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i += k) {
            int a = nums[i];
            // 直接分组会使不能均分的情况变得难以处理
            int b = i + k >= nums.length ? nums[nums.length - 1] : nums[i + k];
            min = Math.min(min, b - a);
        }
        return min;
    }

    public int minimumDifference(int[] nums, int k) {
        Arrays.sort(nums);
        int min = Integer.MAX_VALUE;
        // 需要证明对于 a b c，c-a一定是小于b的，这个比较困难,这就相当于，我们可以舍去b的判断，从0开始到n-k结束即可
        // 注意这个循环的约束条件，这个是相当重要的
        for (int i = 0; i < nums.length - k + 1; i++) {
            min = Math.min(min, nums[i + k - 1] - nums[i]);
        }
        return min;
    }
}
