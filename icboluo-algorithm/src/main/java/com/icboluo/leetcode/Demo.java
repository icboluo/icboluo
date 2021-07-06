package com.icboluo.leetcode;

import com.icboluo.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author icboluo
 * @date 2021-32-03 13:32
 */
public class Demo {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
//        定义辅助级别
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
//            里面存储的是当前级别中的所有元素
            int count = queue.size();
            List<Integer> subList = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                TreeNode poll = queue.poll();
//                其实queue里面存储的值均！=null，用size去遍历，poll值不会为null
                if (poll.left != null) {
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                }
                subList.add(poll.val);
            }
            res.add(subList);
        }
        return res;
    }
}
