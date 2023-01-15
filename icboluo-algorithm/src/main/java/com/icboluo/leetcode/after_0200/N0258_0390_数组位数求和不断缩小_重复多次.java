package com.icboluo.leetcode.after_0200;

import java.util.stream.IntStream;

/**
 * @author icboluo
 * @since 2023-01-14 17:45
 */
class N0258_0390_数组位数求和不断缩小_重复多次 {
    public static void main(String[] args) {
        N0258_0390_数组位数求和不断缩小_重复多次 cla = new N0258_0390_数组位数求和不断缩小_重复多次();
        System.out.println(cla.digitSum("11111222223", 3));
    }

    /**
     * 0258  添加数字，和下面的题一样，一次加1位;每位数相加
     *
     * @param num
     * @return
     */
    public int addDigits(int num) {
        while (num >= 10) {
            int sum = 0;
            while (num != 0) {
                sum += num % 10;
                num = num / 10;
            }
            num = sum;
        }
        return num;
    }

    /**
     * 2243 计算字符串中的数字和，将字符串分组，求和，直到只剩一组
     * 经过尝试，字符串操作之后变成字符串，是一个完整的循环，先把字符串转换为数组或者list属于画蛇添足，整个流程就要加上了，是没有必要的
     *
     * @param s 11111222223
     * @param k
     * @return
     */
    public String digitSum(String s, int k) {
        while (s.length() > k) {
            String temp = "";
            final String str = s;
            for (int i = 0; i < s.length(); i += k) {
                int sum = IntStream.range(i, Math.min(i + k, s.length()))
                        .map(a -> Character.getNumericValue(str.charAt(a)))
                        .sum();
                temp += sum;
            }
            s = temp;
        }
        return s;
    }

    /**
     * 1945 转换后字符串的位数之和，k为重复操作次数
     * 数组位数相加缩减
     *
     * @param s
     * @param k
     * @return
     */
    public int getLucky(String s, int k) {
        String str = "";
        for (int i = 0; i < s.length(); i++) {
            str += s.charAt(i) - 'a' + 1;
        }
        for (int i = 0; i < k; i++) {
            int sum = 0;
            for (int j = 0; j < str.length(); j++) {
                sum += Character.getNumericValue(str.charAt(j));
            }
            str = "" + sum;
        }
        return Integer.parseInt(str);
    }

    /**
     * 2293 最小最大游戏 就是求2个数最小值，再求下2个数最大值..数组不断缩小
     * 代码边界均需要慢慢调整
     *
     * @param nums
     * @return
     */
    public int minMaxGame(int[] nums) {
        for (int j = 0; j < Math.sqrt(nums.length); j++) {
            for (int i = 0; i < nums.length / (2 * (j + 1)); i += 2) {
                nums[i] = Math.min(nums[i * 2], nums[i * 2 + 1]);
                // 避免越界
                if (i * 2 + 2 < nums.length) {
                    nums[i + 1] = Math.max(nums[i * 2 + 2], nums[i * 2 + 3]);
                }
            }
        }
        return nums[0];
    }

    /**
     * 0390 消除游戏 n代表1->n的数组，先从左到右相隔一个删，再从右往左相隔一个删，剩一个返回 12期待6 FIXME ERROR
     *
     * @param n
     * @return
     */
    public int lastRemaining(int n) {
        int[] arr = IntStream.range(1, n + 1).toArray();
        // 外置len可以方便我们计算外层循环层数，可以简单的使用while循环判断执行到了没有，而不是准确判断执行次数，计算这个是比较麻烦的
        int len = arr.length;
        while (len > 1) {
            int j = 0;
            for (int i = 1; i < len; i += 2) {
                arr[j++] = arr[i];
            }
            int k = j / 2;
            len = k;
            for (int i = j - 2; i >= 0; i -= 2) {
                arr[--k] = arr[i];
            }
        }
        return arr[0];
    }
}
