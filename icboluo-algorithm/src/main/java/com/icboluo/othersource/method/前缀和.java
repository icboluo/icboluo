package com.icboluo.othersource.method;

/**
 * 算出一共有几个和为 k 的子数组
 *
 * @author icboluo
 * @since 2021-34-27 21:34
 */
public class 前缀和 {
    public static void main(String[] args) {
        前缀和 cla = new 前缀和();
        int[] arr = {3, 5, 2, -2, 6, 4};
        int i = cla.subarraySum(arr, 8);
        System.out.println("i = " + i);
    }

    /**
     * @param arr
     * @return
     */
    private int subarraySum(int[] arr, int target) {
//        构造一个前缀和的数组
        int[] preSumArr = new int[arr.length + 1];
        preSumArr[0] = 0;
        for (int i = 0; i < arr.length; i++) {
            preSumArr[i + 1] = arr[i] + preSumArr[i];
        }
        int count = 0;
//        求0-i所有 i的前缀和和每一个前缀和相加，一共等于多少个target
        for (int i = 1; i <= arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (preSumArr[i] - preSumArr[j] == target) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * 用map去降低成O(n)复杂度
     *
     * @param arr
     * @param target
     * @return
     */
    private int subarraySum2(int[] arr, int target) {
        return 0;
    }
}
