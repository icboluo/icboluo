package com.icboluo.leetcode.after_1700;

import java.util.Arrays;

/**
 * @author icboluo
 * @since 2023-03-16 22:46
 */
class N2037_使每位学生都有座位的最少移动次数 {
    /**
     * 每个人都坐下的最少移动次数 low
     *
     * @param seats    座位坐标
     * @param students 学生坐标
     * @return
     */
    public int minMovesToSeat(int[] seats, int[] students) {
        Arrays.sort(seats);
        Arrays.sort(students);

        int res = 0;
        for (int i = 0; i < seats.length; i++) {
            int a = Math.abs(seats[i] - students[i]);
            res += a;
        }
        return res;
    }
}
