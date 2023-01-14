package com.icboluo.leetcode.after_0100;

import java.util.ArrayList;
import java.util.List;

class N0118_0119_2221_杨辉三角_当前行数组可以由上一行相加推出来 {
    public static void main(String[] args) {
        var cla = new N0118_0119_2221_杨辉三角_当前行数组可以由上一行相加推出来();
        List<List<Integer>> generate = cla.generate(8);
        System.out.println("generate = " + generate);
        System.out.println(cla.getRow(3));
    }

    public List<List<Integer>> generate(int n) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> list0 = new ArrayList<>();
        list0.add(1);
        ans.add(list0);
        for (int i = 1; i < n; i++) {
            List<Integer> listi = new ArrayList<>();
            List<Integer> pre = ans.get(i - 1);
            listi.add(1);
            for (int j = 1; j < i; j++) {
                listi.add(pre.get(j - 1) + pre.get(j));
            }
            listi.add(1);
            ans.add(listi);
        }
        return ans;
    }

    /**
     * 0119 求杨辉三角的最后一行
     * 挺复杂的题目，需要多次debug
     *
     * @param rowIndex
     * @return
     */
    public List<Integer> getRow(int rowIndex) {
        List<Integer> res = new ArrayList<>();
        res.add(1);
        // 第三行，其实是索引为3的行，索引要去等号
        for (int i = 1; i <= rowIndex; i++) {
            // 用正向循环会因为提前set了原有值导致计算错误，需要反向循环
            for (int j = i - 1; j > 0; j--) {
                int temp = res.get(j - 1) + res.get(j);
                // 看看这个set，完美的使用一个列表去处理这件事
                res.set(j, temp);
            }
            res.add(1);
        }
        return res;
    }

    /**
     * 2221 查找数组的三角和
     * 数组元素22相加，递推出的唯一元素
     *
     * @param nums
     * @return
     */
    public int triangularSum(int[] nums) {
        // 每次操作减少一个元素，总共需要操作n-1次
        for (int i = 0; i < nums.length - 1; i++) {
            // 操作元素越来越少
            for (int j = 0; j < nums.length - i - 1; j++) {
                nums[j] = (nums[j] + nums[j + 1]) % 10;
            }
        }
        return nums[0];
    }
}
