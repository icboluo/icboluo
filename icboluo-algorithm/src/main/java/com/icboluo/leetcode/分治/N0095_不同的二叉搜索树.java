package com.icboluo.leetcode.分治;

import com.icboluo.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author icboluo
 * @see com.icboluo.leetcode.dp.N0096_不同的二叉搜索树
 * @see com.icboluo.leetcode.after_0000.N0014_最长公共前缀#longestCommonPrefix5(String[])
 * @since 2024-04-25 21:48
 */
class N0095_不同的二叉搜索树 {
    public List<TreeNode> generateTrees(int n) {
        return generateTree(1, n);
    }

    /**
     * 分治：将区间分成left，right
     * left区间和right区间元素相互组合
     *
     * @param start 开始节点
     * @param end   结束节点
     * @return 数集合
     */
    private List<TreeNode> generateTree(int start, int end) {
        List<TreeNode> list = new ArrayList<>();
        if (start > end) {
            list.add(null);
            return list;
        }
        if (start == end) {
            list.add(new TreeNode(start));
            return list;
        }
        for (int i = start; i <= end; i++) {
            List<TreeNode> left = generateTree(start, i - 1);
            List<TreeNode> right = generateTree(i + 1, end);
            for (TreeNode leftNode : left) {
                for (TreeNode rightNode : right) {
                    TreeNode curNode = new TreeNode(i);
                    curNode.left = leftNode;
                    curNode.right = rightNode;
                    list.add(curNode);
                }
            }
        }
        return list;
    }
}
