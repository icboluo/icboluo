package com.icboluo.leetcode.tree;

import com.icboluo.common.ListNode;
import com.icboluo.common.TreeNode;
import org.junit.jupiter.api.Test;

/**
 * @author icboluo
 * @since 2022-08-23 18:51
 */
class Demo {

    /**
     * 倒序打印单链表节点的值
     */
    @Test
    public void printListNode() {
        ListNode listNode = new ListNode(1, 2, 3, 4, 5);
        printListNode(listNode);
    }

    private void printListNode(ListNode root) {
        if (root == null) {
            return;
        }
        printListNode(root.next);
        // 后序遍历
        System.out.println(root.val);
    }


    /**
     * 二叉树的解题可以分为2种
     * 1.一次遍历获取结果，这种解题思路往往伴随着回溯
     * 2.通过分解问题获取答案，这种题属于动态规划的解法
     * 二叉树的最大深度
     */
    private void N0104() {
        TreeNode root = new TreeNode();
        list(root);
    }

    int ans = 0;
    int depth = 0;

    private void list(TreeNode root) {
        if (root == null) {
            return;
        }
        depth++;
        if (root.left == null && root.right == null) {
            ans = Math.max(ans, depth);
        }
        list(root.left);
        list(root.right);
        depth--;
    }
}
