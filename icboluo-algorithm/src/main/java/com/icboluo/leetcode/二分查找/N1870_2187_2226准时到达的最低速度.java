package com.icboluo.leetcode.二分查找;

/**
 * @author icboluo
 * @since 2023-03-29 20:37
 */
class N1870_2187_2226准时到达的最低速度 {
    /**
     * FIXME ERROR
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
            for (int i = 0; i < dist.length; i++) {
                // 向上取整，进1
                sum += Math.ceil((double) dist[i] / mid);
            }
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
     * 2187 完成行程的最小时间 FIXME ERROR
     *
     * @param time       完成一次旅途花费的时间
     * @param totalTrips 总共应该进行的旅程次数
     * @return 至少完成total旅途花费的最小时间
     */
    public long minimumTime(int[] time, int totalTrips) {
        long left = 0;
        long right = (int) Math.pow(10, 14);
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
        int sum = 0;
        for (int item : time) {
            // 旅途次数
            sum += mid / item;
        }
        return sum;
    }

    /**
     * 2226 最大可能的糖果数 TODO 二分查找类题目，还有很多 FIXME ERROR
     *
     * @param candies
     * @param k
     * @return
     */
    public int maximumCandies(int[] candies, long k) {
        int left = 0;
        int right = 1000000;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            int sum = 0;
            // 这里比较巧妙，把糖果每堆分给孩子，有的糖果可以分2堆，有的1堆都分不到，尽可能的使糖果的堆数和孩子的个数匹配
            for (int candy : candies) {
                sum += candy / mid;
            }
            if (sum > k) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}
