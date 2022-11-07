package com.icboluo.leetcode.after_0200;

/**
 * @author icboluo
 * @since 2022-11-07 21:30
 */
class N0495_提莫攻击_区间合并 {
    // TODO ERROR，需要刷新间隔
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int res = 0;
        int curTime = 0;
        for (int timeSery : timeSeries) {
            curTime = timeSery + duration - 1;
            res += duration;
        }
        return res;
    }
}
