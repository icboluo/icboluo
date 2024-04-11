package com.icboluo.leetcode.峰值低谷问题;

/**
 * @author icboluo
 * @since 2024-02-26 22:36
 */
class N0121_0122_0123买卖股票的最佳时机 {
    public static void main(String[] args) {
        var cla = new N0121_0122_0123买卖股票的最佳时机();
        System.out.println(cla.maxProfit1(new int[]{3, 3, 5, 0, 0, 3, 1, 4}));
    }

    /**
     * 只能买卖一次 TODO 好像有点看不懂了
     * 求左边最小，右边最大即可，这个方案采取的策略是跟进，每次出现降序的时候，将left指向right，所以left指针永远代表谷底
     *
     * @param arr
     * @return
     */
    public int maxProfit1(int[] arr) {
        // 左指针就是最小指针
        int left = 0;
        int right = 1;
        int max = 0;
        while (right < arr.length) {
            if (arr[left] < arr[right]) {
                max = Math.max(max, arr[right] - arr[left]);
            } else {
                left = right;
            }
            right++;
        }
        return max;
    }

    public int maxProfit2(int[] prices) {
        // min 永远代表路过的最小值，max代表当前的最大值，有点类似于dp思想
        // 2种解法在谷底的寻找上是相似的
        int min = Integer.MAX_VALUE;
        int max = 0;

        for (int price : prices) {
            if (price < min) {
                min = price;
            }
            max = Math.max(max, price - min);
        }
        return max;
    }

    /**
     * N0122_可以多次买入卖出
     *
     * @param prices
     * @return
     */
    public int maxProfit3(int[] prices) {
        int sum = 0;
        int i = 0;
        while (i < prices.length - 1) {
            // 峰底，需要增加一个=号，表示非严格递增或者递减
            while (i < prices.length - 1 && prices[i + 1] <= prices[i]) {
                i++;
            }
            int a = prices[i];
            // 峰顶
            while (i < prices.length - 1 && prices[i + 1] >= prices[i]) {
                i++;
            }
            int b = prices[i];
            sum += b - a;
        }
        return sum;
    }

    // N0123 最多只能交易2笔
    public int maxProfit4(int[] prices) {
        int buyOne = Integer.MAX_VALUE;
        int profileOne = 0;
        int buyTwo = Integer.MAX_VALUE;
        int profileTwo = 0;
        for (int price : prices) {
            buyOne = Math.min(buyOne, price);
            // 当前的利润=当前的购买价格-最低价格
            profileOne = Math.max(profileOne, price - buyOne);
            // 第一次我获取了100的利润，第二次购买价格300相当于实际上是300-100=200的价格
            buyTwo = Math.min(buyTwo, price - profileOne);
            // 第二次的利润和就相当于当前的价格-当前的购买价格
            profileTwo = Math.max(profileTwo, price - buyTwo);
        }
        return profileTwo;
    }


    // TODO 完全看不懂
    public static int maxProfit5(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }
        int k = 2;
        // 当次数大于1半的时候相当于无限买卖，是另一种解决方案
        if (k >= prices.length / 2) {
            int maxProfit = 0;
            for (int i = 1; i < prices.length; ++i) {
                if (prices[i] > prices[i - 1]) {
                    maxProfit += prices[i] - prices[i - 1];
                }
            }
            return maxProfit;
        }
        // 在第i天的最大利润
        int[] f = new int[k + 1];
        // 在第i天卖出的最大利润
        int[] g = new int[k + 1];
        for (int i = 1; i < prices.length; ++i) {
            int diff = prices[i] - prices[i - 1], temp = f[0];
            for (int kk = 1; kk <= k; ++kk) {
                g[kk] = Math.max(g[kk], temp) + diff;
                temp = f[kk];
                f[kk] = Math.max(f[kk], g[kk]);
            }
        }
        return f[k];
    }
}
