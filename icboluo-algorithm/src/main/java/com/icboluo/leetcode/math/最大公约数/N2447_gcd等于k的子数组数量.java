package com.icboluo.leetcode.math.最大公约数;

/**
 * @author icboluo
 * @since 2022-12-08 22:55
 */
class N2447_gcd等于k的子数组数量 {
    // 子数组是连续的，并不是子序列
    public int subarrayGCD(int[] arr, int k) {
        // 这个题不要试图用滑动窗口解决，不如暴力解
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == k) {
                res++;
            }
            int curGcd = arr[i];
            // 以当前元素为起点，向后遍历尝试
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < k || curGcd < k) {
                    break;
                }
                curGcd = gcd(arr[j], curGcd);
                if (curGcd == k) {
                    res++;
                }
            }
        }
        return res;
    }

    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}
