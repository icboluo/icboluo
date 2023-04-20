package com.icboluo.leetcode.after_2000;

import java.util.Arrays;

/**
 * @author icboluo
 * @since 2023-04-20 23:00
 */
class N2383_赢得比赛的最少训练时间 {
    /**
     * 每次训练可以增加一个经验/一个能量
     * 击败对手会增加经验，减少能量
     *
     * @param initialEnergy     初始能量
     * @param initialExperience 初始经验
     * @param energy            减少的能量
     * @param experience        增加的经验
     * @return
     */
    public int minNumberOfHours(int initialEnergy, int initialExperience, int[] energy, int[] experience) {
        // 对于能量来说，初始能量应该大于等于所有的能量
        int res = 0;
        int sum = Arrays.stream(energy).sum();
        if (initialEnergy <= sum) {
            res = sum - initialEnergy + 1;
        }
        // 对于经验来说，一步一步慢慢计算
        for (int ex : experience) {
            if (initialExperience <= ex) {
                res += ex - initialExperience + 1;
                initialExperience = ex + 1;
            }
            initialExperience += ex;
        }
        return res;
    }
}
