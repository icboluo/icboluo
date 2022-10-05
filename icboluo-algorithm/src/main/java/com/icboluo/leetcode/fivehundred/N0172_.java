package com.icboluo.leetcode.fivehundred;

class N0172_ {
    public static void main(String[] args) {
        N0172_ cla = new N0172_();
        int res = cla.m1(5);
        System.out.println("res = " + res);
    }

    /**
     * 阶乘
     *
     * @param n
     * @return
     */
    private int m1(int n) {
        if (n == 1) {
            return n;
        }
        int res = n * m1(n - 1);
        return res;
    }

    /**
     * 2和5解法，因为0是由2*5得来的，并且，5比2要少，所以，5的个数决定了0的个数；对于25来说，由2个5构成
     *
     * @param n
     * @return
     */
    private int m2(int n) {
        if (n == 1) {
            return n;
        }
        int res = n * m1(n - 1);
        return res;
    }
}
