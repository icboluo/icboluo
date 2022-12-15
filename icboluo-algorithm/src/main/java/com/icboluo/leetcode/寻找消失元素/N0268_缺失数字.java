package com.icboluo.leetcode.寻找消失元素;

import java.util.Arrays;

/**
 * @author icboluo
 * @since 2022-11-26 16:56
 */
class N0268_缺失数字 {
    public static void main(String[] args) {
        N0268_缺失数字 cla = new N0268_缺失数字();
        int[] arr = new int[]{0, 1, 2, 3, 4, 5, 7, 8, 9};
        int i = cla.missingNumber3(arr);
        System.out.println("i = " + i);
    }

    /**
     * 方式1：排序，挨个遍历|二分查找
     * 方式2：存储到set,用n.for去set中查找
     * <p>
     * 二分法
     *
     * @param arr
     * @return
     */
    public int missingNumber1(int[] arr) {
        Arrays.sort(arr);
        int left = 0;
        // 这个右左边很重要；因为针对于0123这样的数据而言，left为len-1，right也为len-1，下面小括号显示是开区间，右指针必须开
        int right = arr.length;
        while (left < right) {
//            int mid = right - (right - left) / 2; 假设right=1，left=0---》1，中间指针会向右移动，是不合理的；这个非常重要
            int mid = left + (right - left) / 2;
//            int mid = (left + right) / 2;
            if (arr[mid] > mid) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    /**
     * 求和,1-n的和减去原来的就是结果
     *
     * @param arr
     * @return
     */
    public int missingNumber2(int[] arr) {
        int n = arr.length;
        int sum = (0 + n) * (n + 1) / 2;
        int temp = 0;
        for (int i = 0; i < arr.length; i++) {
            temp += arr[i];
        }
        return sum - temp;
    }

    /**
     * 异或法
     */
    public int missingNumber3(int[] arr) {
        int temp = arr.length;
        for (int i = 0; i < arr.length; i++) {
            temp ^= i;
            temp ^= arr[i];
        }
        return temp;
    }

    /**
     * 方式4：等差数列前n项和：（首项+末项）*项数/2
     *
     * @param arr
     * @return
     */
    public int missingNumber4(int[] arr) {
        int sx = 0;
        int mx = arr.length;
        int xs = arr.length + 1;
        int sum = (sx + mx) * xs / 2;
        int temp = 0;
        for (int i = 0; i < arr.length; i++) {
            temp += arr[i];
        }
        return sum - temp;
    }

    /**
     * 方式4的整形溢出问题
     *
     * @param arr
     * @return
     */
    public int missingNumber5(int[] arr) {
        int res = arr.length;
        for (int i = 0; i < arr.length; i++) {
            // 边加边减吧
            res += i - arr[i];
        }
        return res;
    }
}
