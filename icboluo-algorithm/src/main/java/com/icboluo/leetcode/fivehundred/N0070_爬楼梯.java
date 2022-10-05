package com.icboluo.leetcode.fivehundred;

class N0070_爬楼梯 {
    public static void main(String[] args) {
        /*
        假设你正在爬楼梯，需要n阶你才能到达楼顶
        每次你可以爬1或2个台阶。你有多少种不同的方法可以爬到楼顶呢
         */
        N0070_爬楼梯 cla = new N0070_爬楼梯();
        int i = 3;
        int plt = cla.plt2(i);
        System.out.println("plt = " + plt);
    }

    private int plt2(int target) {
        if (target == 1) {
            return 1;
        }
        int iJian1 = 1;
        int iJian2 = 2;
        int temp;
        for (int i = 2; i < target; i++) {
            temp = iJian2;
            iJian2 = iJian1;
            iJian1 = iJian1 + temp;
        }
        return iJian1;
    }

    private int plt1(int target) {
        if (target == 1) {
            return 1;
        }
        // 状态转移方程：第i阶可以由（i-1）+1，（i-2）+2得到
        // 代表爬到每个楼梯的方法数
        int[] arr = new int[target];
        arr[0] = 1;
        arr[1] = 2;
        for (int i = 2; i < target; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }
        return arr[target - 1];
    }
}
