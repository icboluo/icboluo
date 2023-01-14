package com.icboluo.leetcode.前缀和;

import java.util.HashMap;
import java.util.Map;

/**
 * @author icboluo
 * @since 2023-01-08 12:45
 */
class N0560_和为k的子数组 {
    public static void main(String[] args) {
        var cla = new N0560_和为k的子数组();
        int i = cla.subarraySum2(new int[]{-1, -1, 1}, 0);
        System.out.println("i = " + i);
    }

    public int subarraySum1(int[] arr, int k) {
        int[] preSum = preSum(arr);
        int count = 0;
        for (int i = 0; i < preSum.length; i++) {
            for (int j = 0; j < i; j++) {
                // 2个前缀和相减及是所求
                int temp = preSum[i] - preSum[j];
                if (temp == k) {
                    count++;
                }
            }
        }
        return count;
    }

    private int[] preSum(int[] arr) {
        // 多了第一位的前缀和
        int[] preSum = new int[arr.length + 1];
        preSum[0] = 0;
        for (int i = 0; i < arr.length; i++) {
            preSum[i + 1] = preSum[i] + arr[i];
        }
        return preSum;
    }

    /**
     * @param arr
     * @param k
     * @return
     */
    public int subarraySum2(int[] arr, int k) {
        int[] preSum = new int[arr.length + 1];
        // 前缀和出现次数
        Map<Integer, Integer> map = new HashMap<>();
        // 0 出现1次代表如果当前的前缀和就是所求结果，count+1，也就是说，
        // map不应该将当前的前缀和放进去参与运算（map的put应该放在后面，这个是不好理解的
        map.put(0, 1);
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            // 前缀和并不是一次性构建的，每次只是构建前面的元素，这样遍历过程中相当于j=0;j<i
            preSum[i] = i == 0 ? arr[i] : preSum[i - 1] + arr[i];
            int need = preSum[i] - k;
            if (map.containsKey(need)) {
                count += map.get(need);
            }
            // 因为单词循环是针对i以前的元素，所以不要将i之前的元素也放进去;这个put需要统计完成之后的结果
            map.put(preSum[i], map.getOrDefault(preSum[i], 0) + 1);
        }
        return count;
    }
}
