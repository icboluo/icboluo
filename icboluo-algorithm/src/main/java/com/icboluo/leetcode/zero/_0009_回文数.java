package com.icboluo.leetcode.zero;

/**
 * @author icboluo
 * @date 2020-09-27 20:55
 */
public class _0009_回文数 {
    public static void main(String[] args) {
        int i = 121;
        boolean b = m1(i);
        System.out.println("b = " + b);
    }

    /**
     * 用sb的reverse方法判断
     *
     * @param i
     */
    private static boolean m1(int i) {
        StringBuilder sb = new StringBuilder(i + "");
        return sb.equals(sb.reverse());
    }

    /**
     * 用/取第1位，%取最后一位，依次往中间比较
     *
     * @param i
     * @return
     */
    private static boolean m2(int i) {
        return false;
    }

    /**
     * 取数字的前半段和后半段进行比较
     *
     * @param i
     * @return
     */
    private static boolean m3(int i) {
        return false;
    }
}
