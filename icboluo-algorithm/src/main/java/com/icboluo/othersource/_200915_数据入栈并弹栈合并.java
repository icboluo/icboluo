package com.icboluo.othersource;

/**
 * @author icboluo
 * @since 2020-09-27 21:05
 */
class _200915_数据入栈并弹栈合并 {
    public static void main(String[] args) {
        int[] arr = {12, 6, 1, 2, 3, 1};
        m1(arr);
    }

    /**
     * 如果栈中元素可以合并，则更新栈
     * 如果不能合并，更新新的list，并还原栈
     * 直到栈空
     */
    private static void m1(int[] arr) {
        if (arr.length == 0) {
            return;
        }
        if (arr.length == 1) {
            System.out.println(arr[0]);
        }
        int i = arr.length - 2;
        int r = arr.length - 1;
        int sum = 0;
        while (i >= 0) {
            sum += arr[i];
            // 还需要迭代
            if (sum < arr[r]) {
                i--;
                // 刚好
            } else if (sum == arr[r]) {
                arr[r] = 2 * arr[r];
                sum = 0;
                i--;
                // 不合理
            } else {
                System.out.println(arr[r]);
                r = i;
                i--;
                sum = 0;
            }
        }
        if (sum == 0) {
            System.out.println(arr[r]);
        } else {
            System.out.println(arr[r]);
            System.out.println(sum);
        }
    }

}
