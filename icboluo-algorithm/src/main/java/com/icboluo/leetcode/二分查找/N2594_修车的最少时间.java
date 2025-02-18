package com.icboluo.leetcode.二分查找;

/**
 * @author icboluo
 * @since 2025-02-18 13:04
 */
class N2594_修车的最少时间 {
    public static void main(String[] args) {
        var cla = new N2594_修车的最少时间();
        int[] arr = {4, 2, 3, 1};
        System.out.println(cla.repairCars(arr, 10));
    }

    public long repairCars(int[] ranks, int cars) {
        long left = 0;
        long right = Long.MAX_VALUE;
        // 双闭区间
        while (left <= right) {
            long mid = left + ((right - left) >> 1);
            int sum = aa(ranks, mid);
            // 汽车总数太大了，给的时间太多了
            if (cars < sum) {
                right = mid - 1;
            } else if (cars > sum) {
                left = mid + 1;
            } else {
                // =的时候，我们尝试再调小一点
                right = mid - 1;
            }
        }
        // 这块求的是左边界，所以这块要判断
        // 1. left 是否超过最大right，如果超过，错误 -----------因为这块最大right为max值，所以不可能
        // 2. arr[left] 是否不等于target，如果等于，错误 -------因为这块我们要求满足条件即可，所以arr[left]大一点是允许的
        return left;
    }

    private int aa(int[] ranks, long time) {
        int sum = 0;
        for (int rank : ranks) {
            sum += Math.sqrt(time / rank);
        }
        return sum;
    }
}
