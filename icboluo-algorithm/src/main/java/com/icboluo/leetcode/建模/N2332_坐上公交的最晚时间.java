package com.icboluo.leetcode.建模;

import java.util.Arrays;

/**
 * @author icboluo
 * @since 2023-03-29 20:19
 */
class N2332_坐上公交的最晚时间 {
    /**
     * 这个代码写得非常难理解 TODO hard to understand
     *
     * @param buses      公交车的出发时间（唯一
     * @param passengers 乘客的到达时间（唯一
     * @param capacity   每辆公交车最多容纳乘客数
     * @return 最晚到达公交车的时间
     */
    public int latestTimeCatchTheBus(int[] buses, int[] passengers, int capacity) {
        Arrays.sort(buses);
        Arrays.sort(passengers);
        int j = 0;
        for (int i = 0; i < buses.length; i++) {
            int temp = 0;
            // 上车：当到达时间点比较早，并且车没有满
            while (j < passengers.length && passengers[j] <= buses[i] && temp < capacity) {
                temp++;
                // 下一个乘客
                j++;
            }
            // 最后一辆车
            if (i == buses.length - 1) {
                // 车没满
                if (temp < capacity) {
                    // 汽车到达的时间，如果车没满，说明我们按车来的时间倒序处理即可
                    int buse = buses[i];
                    // 从最晚时间倒序，直到没有乘客为止
                    for (int k = j - 1; k >= 0 && passengers[k] == buse; k--) {
                        buse--;
                    }
                    return buse;
                } else {
                    // 乘客到达的时间，如果车满了，我们就要考虑乘客到达时间，从最后一个乘客到达的时间地上一秒开始，找一个空隙
                    int te = passengers[j - 1] - 1;
                    for (int k = j - 2; k >= 0 && passengers[k] == te; k--) {
                        te--;
                    }
                    return te;
                }
            }
        }
        return -1;
    }
}
