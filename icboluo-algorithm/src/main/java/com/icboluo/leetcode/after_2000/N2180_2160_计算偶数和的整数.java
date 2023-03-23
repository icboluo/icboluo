package com.icboluo.leetcode.after_2000;

import java.util.Arrays;

/**
 * @author icboluo
 * @since 2023-01-15 19:25
 */
class N2180_2160_计算偶数和的整数 {
    /**
     * 统计各位数字之和为偶数的整数个数 暴力解
     *
     * @param num
     * @return
     */
    public int countEven(int num) {
        int res = 0;
        for (int i = 0; i < num; i++) {
            if (sumIsEven(i + 1)) {
                res++;
            }
        }
        return res;
    }

    private boolean sumIsEven(int num) {
        // 漂亮的Stream链式调用，与字符数字转换
        return String.valueOf(num).chars().map(Character::getNumericValue).sum() % 2 == 0;
    }

    /**
     * 2160 拆分数字后四位数字 的最小和
     * num就4位，组合2个，求最小和
     * 这样思考，排序好像能解决这个问题
     *
     * @param num
     * @return
     */
    public int minimumSum(int num) {
        char[] arr = (num + "").toCharArray();
        Arrays.sort(arr);
        return Integer.parseInt("" + arr[0] + arr[2]) + Integer.parseInt("" + arr[1] + arr[3]);
    }

    // 2310 个位数k的数字总和：num拆成2个数，2个数个位均为k，求有几组，应该可以归纳为db问题，不会
    // 2443 一个数是否可以拆分为a和a反着写之和，不会

    /**
     * 2177 找出总和等于给定数的三个连续整数 3个连续的数之和等于目标值
     * 复杂的方式不会，简单的方式就很简单了
     *
     * @param num
     * @return
     */
    public long[] sumOfThree(long num) {
        if (num % 3 != 0) {
            return new long[0];
        }
        long mid = num / 3;
        return new long[]{mid - 1, mid, mid + 1};
    }

    /**
     * 2240 购买钢笔和铅笔的方式数量
     * 可以花完或者花不完
     *
     * @param total 钱
     * @param cost1 钢笔价格
     * @param cost2 铅笔价格
     * @return 返回组合数
     */
    public long waysToBuyPensPencils(int total, int cost1, int cost2) {
        long res = 0;
        for (int i = 0; i <= total / cost1; i++) {
            // 后面的加1是因为0也是一种分配方式
            res += (total - (long) cost1 * i) / cost2 + 1;
        }
        return res;
    }

    /**
     * 2274 没有特殊楼层的最大连续楼层数
     *
     * @param bottom  开始
     * @param top     结束
     * @param special 特殊楼层
     * @return
     */
    public int maxConsecutive(int bottom, int top, int[] special) {
        Arrays.sort(special);
        int left = 0;
        if (bottom < special[0]) {
            left = special[0] - bottom;
        }
        int right = 0;
        if (top > special[special.length - 1]) {
            right = top - special[special.length - 1];
        }
        // mid 已经是左右最大值了，再填充上中间的最大值即可
        int mid = Math.max(left, right);
        for (int i = 0; i < special.length - 1; i++) {
            // 这里需要减1
            mid = Math.max(mid, special[i + 1] - special[i] - 1);
        }
        return mid;
    }

    // 0164 最大间隔 TODO hard 桶排序

    /**
     * 2414 最长 字母连续子串 长度
     *
     * @param s
     * @return
     */
    public int longestContinuousSubstring(String s) {
        int left = 0;
        int right = 1;
        // s长度为1时，返回1，取特例
        int max = 1;
        while (right < s.length()) {
            if (s.charAt(right) - s.charAt(right - 1) == 1) {
                right++;
                max = Math.max(max, right - left);
            } else {
                left = right++;
            }
        }
        return max;
    }
}
