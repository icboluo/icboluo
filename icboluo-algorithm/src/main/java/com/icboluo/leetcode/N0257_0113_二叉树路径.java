package com.icboluo.leetcode;

import com.icboluo.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author icboluo
 * @date 2022-03-28 22:32
 */
public class N0257_0113_二叉树路径 {

    List<List<TreeNode>> ans;

    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        ans = new ArrayList<>();
        treePath(root, new LinkedList<>());
        return ans.stream()
                .map(li ->
                        li.stream().map(treeNode -> treeNode.val)
                                .map(String::valueOf)
                                .collect(Collectors.joining("->"))
                ).collect(Collectors.toList());
    }

    private void treePath(TreeNode root, LinkedList<TreeNode> curList) {
//        递归不要去管left right，就去add cur
        curList.add(root);
        if (root.left == null && root.right == null) {
            ans.add(new ArrayList<>(curList));
        }
        if (root.left != null) {
//            执行一个回溯一个，必须这样
            treePath(root.left, curList);
            curList.removeLast();
        }
        if (root.right != null) {
            treePath(root.right, curList);
            curList.removeLast();
        }
    }

    int targetSum;

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return new ArrayList<>();
        }
        ans = new ArrayList<>();
        this.targetSum = targetSum;
        treePath2(root, new LinkedList<>());
        return ans.stream()
                .map(li ->
                        li.stream().map(treeNode -> treeNode.val)
                                .collect(Collectors.toList())
                ).collect(Collectors.toList());
    }

    private void treePath2(TreeNode root, LinkedList<TreeNode> curList) {
        curList.add(root);
        int sum = curList.stream().map(treeNode -> treeNode.val).mapToInt(Integer::valueOf).sum();
        if (root.left == null && root.right == null && sum == targetSum) {
            ans.add(new ArrayList<>(curList));
        }
        if (root.left != null) {
            treePath2(root.left, curList);
            curList.removeLast();
        }
        if (root.right != null) {
            treePath2(root.right, curList);
            curList.removeLast();
        }
    }
}
