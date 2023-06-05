package com.icboluo.leetcode.tree.层级遍历;

import com.icboluo.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author icboluo
 * @since 2023-06-05 21:37
 */
public class N0993_判断xy是否深度相等并且父节点不相等 {

    public boolean isCousins(TreeNode root, int x, int y) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean aIsExist = false;
            boolean bIsExist = false;
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                assert poll != null;
                if (poll.left != null) {
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                }
                if (poll.left != null && poll.right != null) {
                    if ((poll.left.val == x && poll.right.val == y) || (poll.left.val == y && poll.right.val == x)) {
                        return false;
                    }
                }
                if (poll.val == x) {
                    aIsExist = true;
                }
                if (poll.val == y) {
                    bIsExist = true;
                }
            }
            if (aIsExist && bIsExist) {
                return true;
            }
        }
        return false;
    }
    // TODO dfs 方法简述：我们需要dfs的过程中获取到x的深度和y的深度，并且获取到他们的parent，简单判断即可
}
