package com.icboluo.leetcode.after_0000;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class N0015_3数之和 {
    public static void main(String[] args) {
        var cla = new N0015_3数之和();
        int[] arr = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> res = cla.threeSum1(arr);
        System.out.println("res = " + res);
    }


    /**
     * 3层循环求和不考虑
     * <p>
     * 升序数组+双指针：带区间的三数之和
     * 三数之和是否为0
     *
     * @param arr
     * @return
     */
    public List<List<Integer>> threeSum1(int[] arr) {
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
                    // 这里做一个去重即可，也可以使用set集合先收集，最终处理结果集
                    if (!ans.contains(list)) {
                        ans.add(list);
                    }
                    // 因为left、right已经使用过了，所以这里要进行 ++ --
                    left++;
                    right--;
                    // 这里的加速是没有必要的，每个索引的元素均可以使用，并不是不能相同，所以这块加上加速和break只会让结果集变小
/*                    if (arr[left + 1] == arr[left]) {
                        left++;
                    } else if (arr[right - 1] == arr[right]) {
                        right--;
                    } else {
                        break;
                    }*/
                } else if (temp < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return ans;
    }

    public boolean threeSum2(int[] arr) {
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
