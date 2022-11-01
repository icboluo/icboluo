package com.icboluo.leetcode.after_1000;

import com.icboluo.common.ListNode;
import com.icboluo.common.TreeNode;

/**
 * @author icboluo
 * @since 2022-05-29 17:00
 */
class N1367_二叉树是否存在指定链表 {
    public boolean isSubPath(ListNode head, TreeNode root) {
        return dfs(head, root) || (root != null && (isSubPath(head, root.left) || isSubPath(head, root.right)));
    }

    private boolean dfs(ListNode head, TreeNode root) {
        if (head == null) {
            return true;
        }
        if (root == null) {
            return false;
        }
        return root.val == head.val && (dfs(head.next, root.left) || dfs(head.next, root.right));
    }
}
