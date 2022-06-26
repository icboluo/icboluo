package com.icboluo.leetcode.fivehundred;

/**
 * @author icboluo
 * @since 2022-06-26 17:10
 */
public class N0004_寻找两个正序数组的中位数 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        return findMidNum1(nums1, nums2);
//        return findMidNum2(nums1, nums2);
    }

    /**
     * 合并数组，求中位数
     *
     * @param arr1 数组1
     * @param arr2 数组2
     * @return 中位数
     */
    private double findMidNum1(int[] arr1, int[] arr2) {
        int[] arr = new int[arr1.length + arr2.length];
        int i = 0;
        int i1 = 0;
        int i2 = 0;
        while (i1 < arr1.length && i2 < arr2.length) {
            if (arr1[i1] < arr2[i2]) {
                arr[i++] = arr1[i1++];
            } else {
                arr[i++] = arr2[i2++];
            }
        }
        while (i1 < arr1.length) {
            arr[i++] = arr1[i1++];
        }
        while (i2 < arr2.length) {
            arr[i++] = arr2[i2++];
        }
        // i 就是arr.length
        if (i % 2 == 0) {
            return Double.parseDouble(arr[i / 2 - 1] + arr[i / 2] + "") / 2;
        }
        return arr[arr.length / 2];
    }

    /**
     * 保留左右元素，求中位数
     *
     * @param arr1 数组1
     * @param arr2 数组2
     * @return 中位数
     */
    private double findMidNum2(int[] arr1, int[] arr2) {
        int i1 = 0;
        int i2 = 0;
        int leftEle = -1;
        int rightEle = -1;
        for (int i = 0; i <= (arr1.length + arr2.length) / 2; i++) {
            leftEle = rightEle;
            // 如果arr1中的元素比较小，下一个元素就是arr1中的
            if (i1 < arr1.length && (i2 == arr2.length || arr1[i1] < arr2[i2])) {
                rightEle = arr1[i1++];
            } else {
                rightEle = arr2[i2++];
            }
        }
        if ((arr1.length + arr2.length) % 2 == 0) {
            return Double.parseDouble(leftEle + rightEle + "") / 2;
        }
        return rightEle;
    }

    /**
     * TODO 二分法查找中位数
     *
     * @param arr1 数组1
     * @param arr2 数组2
     * @return 中位数
     */
    private double findMidNum3(int[] arr1, int[] arr2) {
        // 使arr1比较短
        if (arr1.length > arr2.length) {
            int[] temp = arr1;
            arr1 = arr2;
            arr2 = temp;
        }
        int left = 0;
        return left;
    }

    public static void main(String[] args) {
        N0004_寻找两个正序数组的中位数 cla = new N0004_寻找两个正序数组的中位数();
        int[] arr1 = new int[]{1, 2};
        int[] arr2 = new int[]{3, 4};
        double midNum1 = cla.findMidNum1(arr1, arr2);
        System.out.println("midNum1 = " + midNum1);
    }
}
