package com.icboluo.leetcode.二分查找;

import java.util.Arrays;

/**
 * @author icboluo
 * @since 2023-03-29 20:37
 */
class N1870_2187_2226准时到达的最低速度 {
    public static void main(String[] args) {
        var cla = new N1870_2187_2226准时到达的最低速度();
        System.out.println(cla.maximumCandies(new int[]{4, 7, 5}, 16));
    }

    /**
     * 我们需要获得一个速度，速度太慢，会花费更多的时间，不满足时间约束
     * 我们可以尝试二分查找，去找合适的时间
     *
     * @param dist 单次乘坐的距离
     * @param hour 最多花费的时间
     * @return
     */
    public int minSpeedOnTime(int[] dist, double hour) {
        int left = 0;
        int right = (int) Math.pow(10, 7);
        int res = -1;
        while (left <= right) {
            int mid = left + ((right - left) >> 2);
            double sum = 0;
            for (int i = 0; i < dist.length - 1; i++) {
                // 向上取整，进1
                sum += Math.ceil((double) dist[i] / mid);
            }
            // 最后一列班车所用的时间不需要转换成整数
            sum += (double) dist[dist.length - 1] / mid;
            // 时间较大是不合法的
            if (sum > hour) {
                left = mid + 1;
            } else {
                // 最后一次较大或者相等的速度是所求的结果
                res = mid;
                right = mid - 1;
            }
        }
        return res;
    }

    /**
     * 2187 完成行程的最小时间
     *
     * @param time       完成一次旅途花费的时间
     * @param totalTrips 总共应该进行的旅程次数
     * @return 至少完成total旅途花费的最小时间
     */
    public long minimumTime(int[] time, int totalTrips) {
        long left = 0;
        long right = (long) Math.pow(10, 14);
        while (left <= right) {
            long mid = left + ((right - left) >> 1);
            long cal = cal(time, mid);
            // 旅途次数过大，说明时间比较大，我们应该右指针缩小
            if (cal >= totalTrips) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private long cal(int[] time, long mid) {
        long sum = 0;
        for (int item : time) {
            // 旅途次数
            sum += mid / item;
        }
        return sum;
    }

    /**
     * 2226 最大可能的糖果数 TODO 二分查找类题目，还有很多 FIXME ERROR 期待1实际0
     *
     * @param candies
     * @param k
     * @return
     */
    public int maximumCandies(int[] candies, long k) {
        int left = 0;
        int right = Integer.MAX_VALUE;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (mid == 0) {
                return Arrays.stream(candies).sum() == k ? 1 : 0;
            }
            int sum = 0;
            // 这里比较巧妙，把糖果每堆分给孩子，有的糖果可以分2堆，有的1堆都分不到，尽可能的使糖果的堆数和孩子的个数匹配
            for (int candy : candies) {
                sum += candy / mid;
            }
            // 糖果堆数比较多，说明我们分的比较小，需要每堆多分点[mid+1,right]
            // 如果糖果堆数相等，我们需要糖果数变大，取右边界 [mid+1,right]
            if (sum >= k) {
                left = mid + 1;
            } else {
                // [left,mid-1]
                right = mid - 1;
            }
        }
        return left - 1;
    }
}
