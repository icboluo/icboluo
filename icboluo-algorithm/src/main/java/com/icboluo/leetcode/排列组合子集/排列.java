package com.icboluo.leetcode.排列组合子集;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 回溯算法其实就是多叉树的遍历问题
 * 动态规划算法有重叠子问题的性质，可以通过优化，使递归树大幅剪枝
 * <p>
 * 排列问题针对的是无序处理，1,2,3排列后顺序1可能在最后面，所以，元素在数组中的相对位置不重要，我们需要使用额外的是否
 * 使用标记来标识该元素是否被使用过
 * <p>
 * 组合问题针对的是有序处理，1,2,3组合后的顺序一定是1在最前面（因为组合结果不在乎顺序），所以，完全不需要是否使用标识，
 * 只要指针紧紧跟住元素组合即可
 *
 * @author icboluo
 * @since 2022-06-21 20:27
 */
public class 排列 {

    List<List<Integer>> ans;
    /**
     * 轨道/路径
     */
    LinkedList<Integer> track;

    boolean[] used;

    /**
     * N0046 全排列
     *
     * @param nums 待排列数组
     * @return 全排列集合
     */
    List<List<Integer>> permute(int[] nums) {
        ans = new LinkedList<>();
        track = new LinkedList<>();
        used = new boolean[nums.length];
        backtrack1(nums);
        return ans;
    }

    private void backtrack1(int[] arr) {
        if (track.size() == arr.length) {
            ans.add(new LinkedList<>(track));
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            if (used[i]) {
                continue;
            }
            used[i] = true;
            track.add(arr[i]);
            backtrack1(arr);
            track.removeLast();
            used[i] = false;
        }
    }

    /**
     * 回溯算法
     *
     * @param arr 待排列数组/选择列表
     */
    private void backtrack2(int[] arr) {
        // 触发结束条件，必须使用deepCopy，此块也是多叉树叶子节点的值
        if (track.size() == arr.length) {
            ans.add(new LinkedList<>(track));
            return;
        }
        // 做选择
        for (int i = 0; i < arr.length; i++) {
            // 剔除不合法选择
            if (track.contains(arr[i])) {
                continue;
            }
            // 路径.add(选择)
            track.add(arr[i]);
            // 进入下一层决策树
            // backtrack(选择列表)
            backtrack2(arr);
            // 撤销选择
            track.removeLast();
        }
    }

    /**
     * N0047 可重复的全排列
     *
     * @param nums [1,2,2]
     * @return [ [1,2,2],[2,1,2],[2,2,1] ]
     */
    List<List<Integer>> permuteUnique(int[] nums) {
        ans = new LinkedList<>();
        track = new LinkedList<>();
        used = new boolean[nums.length];
        Arrays.sort(nums);
        backtrack3(nums);
//        backtrack4(nums);
        return ans;
    }

    private void backtrack3(int[] arr) {
        if (track.size() == arr.length) {
            ans.add(new LinkedList<>(track));
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            if (used[i]) {
                continue;
            }
            // 和前一个元素相等，并且前一个元素没有使用过（也就是说，后面的元素只有前面的元素被选择的时候才可以使用
            if (i > 0 && arr[i] == arr[i - 1] && !used[i - 1]) {
                continue;
            }
            track.add(arr[i]);
            used[i] = true;
            backtrack3(arr);
            track.removeLast();
            used[i] = false;
        }
    }

    private void backtrack4(int[] arr) {
        if (track.size() == arr.length) {
            ans.add(new LinkedList<>(track));
            return;
        }
        // 随便初始化一个不存在的前驱节点
        int pre = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (used[i]) {
                continue;
            }
            if (arr[i] == pre) {
                continue;
            }
            track.add(arr[i]);
            used[i] = true;
            pre = arr[i];
            backtrack4(arr);
            track.removeLast();
            used[i] = false;
        }
    }

    /**
     * 元素不重复可以复选
     *
     * @param nums [1,2,3]
     * @return ...27个
     */
    public List<List<Integer>> permuteRepeat(int[] nums) {
        ans = new LinkedList<>();
        track = new LinkedList<>();
        backtrack5(nums);
        return ans;
    }

    private void backtrack5(int[] arr) {
        if (track.size() == arr.length) {
            ans.add(new LinkedList<>(track));
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            track.add(arr[i]);
            backtrack5(arr);
            track.removeLast();
        }
    }
}
