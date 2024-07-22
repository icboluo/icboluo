package com.icboluo.leetcode.tree;

import com.icboluo.common.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author icboluo
 * @since 2024-07-22 下午9:13
 */
public class N0863_02383_二叉树当前节点到其他节点的距离 {
    public static void main(String[] args) {
        N0863_02383_二叉树当前节点到其他节点的距离 cla = new N0863_02383_二叉树当前节点到其他节点的距离();
        TreeNode root = new TreeNode(3, 5, 1, 6, 2, 0, 8, null, null, 7, 4);
        TreeNode treeNode5 = new TreeNode(5);
        treeNode5.left = root.left.left;
        treeNode5.right = root.left.right;
        root.left = treeNode5;
        root.print();
        System.out.println(cla.distanceK(root, treeNode5, 2));

        // TODO 这个方法好像有问题
        TreeNode root2 = new TreeNode(1, 5, 3, null, 4, 10, 6, 9, 2);
        root2.print();
        System.out.println(cla.amountOfTime(root2, 3));
    }

    Map<TreeNode, Integer> map = new HashMap<>();

    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> res = new ArrayList<>();
        find(root, target);
        search(root, 10086, K, res);
        return res;
    }

    private void find(TreeNode root, TreeNode target) {
        if (root == null) {
            return;
        }

        if (root == target) {
            map.put(root, 0);
            return;
        }

        find(root.left, target);
        // optimize 二叉树当前节点到其他节点的距离
        // 如果左子树中存在target，则计数；新奇的东西，仅针对于单个子树是后序遍历,并没有遍历完整棵树
        // 因为提前结束，所以map中只会保留从root->target 这一条通路的路径值
        if (map.containsKey(root.left)) {
            map.put(root, map.get(root.left) + 1);
            return;
        }

        find(root.right, target);
        if (map.containsKey(root.right)) {
            map.put(root, map.get(root.right) + 1);
            return;
        }
    }

    public void search(TreeNode root, int dis, int K, List<Integer> res) {
        if (root == null) {
            return;
        }

        // 这里涉及树的2个分支：假如当前要找的target在左子树
        // 如果遍历到左子树，则会走到contain分支，会使用现有的较小的值（更接近target）
        // 如果遍历到右子树，则会走dis+1，距离会根据当前节点进行延长
        if (map.containsKey(root)) {
            dis = map.get(root);
        }

        if (dis == K) {
            res.add(root.val);
        }

        search(root.left, dis + 1, K, res);
        search(root.right, dis + 1, K, res);
    }

    // 2385 二叉树被感染的时间 FIXME
    public int amountOfTime(TreeNode root, int start) {
        ans = 0;
        map = new HashMap<>();
        find(root, start);
        search(root, 0);
        return ans;
    }

    private void find(TreeNode root, int target) {
        if (root == null) {
            return;
        }
        if (root.val == target) {
            map.put(root, 0);
            return;
        }
        find(root.left, target);
        if (map.containsKey(root.left)) {
            map.put(root, map.get(root.left) + 1);
            return;
        }
        find(root.right, target);
        if (map.containsKey(root.right)) {
            map.put(root, map.get(root.right) + 1);
            return;
        }
    }

    int ans = 0;

    private void search(TreeNode root, int dis) {
        ans = Math.max(ans, dis);
        if (root == null) {
            return;
        }
        if (map.containsKey(root)) {
            dis = map.get(root);
        }
        search(root.left, dis + 1);
        search(root.right, dis + 1);
    }
}
