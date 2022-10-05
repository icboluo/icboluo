package com.icboluo.leetcode.fivehundred;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class N0015_3数之和 {
    public static void main(String[] args) {
        int[] arr = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> res = threeNumSum1(arr);
        System.out.println("res = " + res);
    }


    /**
     * 3层循环求和不考虑
     * <p>
     * 升序数组+双指针：带区间的三数之和
     *
     * @param arr
     * @return
     */
    private static List<List<Integer>> threeNumSum1(int[] arr) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(arr);
        for (int i = 0; i < arr.length - 2; i++) {
            int left = i + 1;
            int right = arr.length - 1;
            while (left < right) {
                int temp = arr[i] + arr[left] + arr[right];
                if (temp == 0) {
                    List<Integer> list = new ArrayList<>();
                    list.add(arr[i]);
                    list.add(arr[left]);
                    list.add(arr[right]);
                    ans.add(list);
                    if (arr[left + 1] == arr[left]) {
                        left++;
                    } else if (arr[right - 1] == arr[right]) {
                        right--;
                    } else {
                        break;
                    }
                } else if (temp < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return ans;
    }

    private static boolean threeNumSum2(int[] arr) {
        Arrays.sort(arr);
        for (int i = 0; i < arr.length - 2; i++) {
            // 因为每次从第i个节点开始，left为i个节点的左边一个节点，所以left指针应该写到里面
            int left = i + 1;
            int right = arr.length - 1;
            while (left < right) {
                int sum = arr[i] + arr[left] + arr[right];
                if (sum == 0) {
                    return true;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return false;
    }
}
