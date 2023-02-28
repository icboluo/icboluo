package com.icboluo.leetcode.日期;

/**
 * @author icboluo
 * @since 2023-02-28 21:11
 */
class N1736_替换隐藏数字得到的最晚时间 {
    public static void main(String[] args) {
        N1736_替换隐藏数字得到的最晚时间 cla = new N1736_替换隐藏数字得到的最晚时间();
        System.out.println(cla.countTime("2?:??"));
    }
    // 求最大时间 这些题情况太多了，得好好整理测试用例
    public String maximumTime(String time) {
        char a = time.charAt(0);
        // 24点的特殊情况
        if (time.charAt(0) == '?') {
            if (time.charAt(1) == '?') {
                a = '2';
            } else if (time.charAt(1) >= '4') {
                a = '1';
            } else {
                a = '2';
            }
        }
        char b = time.charAt(1);
        if (time.charAt(1) == '?') {
            if (a == '2') {
                b = '3';
            } else {
                b = '9';
            }
        }
        char c = time.charAt(3) == '?' ? '5' : time.charAt(3);
        char d = time.charAt(4) == '?' ? '9' : time.charAt(4);
        return "" + a + b + ":" + c + d;
    }

    // 2437 有效时钟次数，求一共有多少合理时间
    public int countTime(String time) {
        // 我们可以先求简单的后两位
        int c = time.charAt(3) == '?' ? 6 : 1;
        int d = time.charAt(4) == '?' ? 10 : 1;
        int a;
        // 先求简单的情况，排除一种情况之后剩下的就简单一些
        if (time.charAt(0) == '?' && time.charAt(1) == '?') {
            a = 24;
        } else {
            if (time.charAt(0) == '?') {
                a = time.charAt(1) >= '4' ? 2 : 3;
            } else if (time.charAt(1) == '?') {
                a = time.charAt(0) == '2' ? 4 : 10;
            }else{
                a = 1;
            }
        }
        return a * c * d;
    }

    // 0949 给定数字的最大时间
    // 时间的组合情况太多直接使用暴力解，答案太垃圾了，我会用全排列
    // 当前，直接循环所有的时间，然后判断时间和数组是否相似也是可以的
    public String largestTimeFromDigits(int[] arr) {
        return "";
    }
}
