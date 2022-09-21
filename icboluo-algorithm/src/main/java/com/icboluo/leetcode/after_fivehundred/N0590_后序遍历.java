package com.icboluo.leetcode.after_fivehundred;

import com.icboluo.common.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author icboluo
 * @since 2021-28-09 13:28
 */
public class N0590_后序遍历 {
    public List<Integer> postorder(Node root) {
        return postorder1(root);
//       return postorder2(root);
    }

    List<Integer> ans;

    public List<Integer> postorder1(Node root) {
        if (root == null) {
            return new ArrayList<>();
        }
        ans = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node pop = stack.pop();
//            因为后序需要反转，所以这里需要正序
            for (Node child : pop.children) {
                stack.push(child);
            }
            //            反着新增，当然做完之后反转整个ans也是可以的
            ans.add(0, pop.val);
        }
        return ans;
    }

    public List<Integer> postorder2(Node root) {
        ans = new ArrayList<>();
        dfs(root);
        return ans;
    }

    private void dfs(Node root) {
        if (root == null) {
            return;
        }
        for (Node child : root.children) {
            dfs(child);
        }
        ans.add(root.val);
    }
}
