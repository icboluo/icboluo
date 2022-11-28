package com.icboluo.leetcode.after_0800;

/**
 * @author icboluo
 * @since 2022-11-25 14:12
 */
class N0860_硬币找零 {
    public boolean lemonadeChange(int[] bills) {
        // 5 10 20
        int[] arr = new int[20 + 1];
        for (int bill : bills) {
            arr[bill]++;
            // 15
            if (bill == 20) {
                if (arr[10] > 0) {
                    arr[10]--;
                    bill -= 10;
                } else if (arr[5] >= 2) {
                    arr[5] -= 2;
                    bill -= 10;
                }else{
                    return false;
                }
            }
            if (bill == 10) {
                if (arr[5] > 0) {
                    arr[5]--;
                }else{
                    return false;
                }
            }
        }
        return true;
    }
}
