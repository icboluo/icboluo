package com.icboluo.leetcode.tree;

import com.icboluo.common.TreeNode;
import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractResourceBasedMessageSource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * @author icboluo
 * @since 2021-11-18 0:07
 */
class N0105_0106前序中序构建二叉树 {

    public static void main(String[] args) {
        var cla = new N0105_0106前序中序构建二叉树();
        int[] pre = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        int[] postorder = {9, 15, 7, 20, 3};
        TreeNode treeNode = cla.buildTree(pre, inorder);
        treeNode.print();
        cla.buildTree2(postorder, 0, postorder.length - 1, 0, inorder.length - 1).print();

        cla.constructFromPrePost2(pre, postorder).print();
        cla.constructFromPrePost2(new int[]{1, 2, 4, 5, 3, 6, 7}, new int[]{4, 5, 2, 6, 7, 3, 1}).print();
        cla.constructFromPrePost2(new int[]{1}, new int[]{1}).print();
    }

    Map<Integer, Integer> inorderMap;

    int index;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        inorderMap = new HashMap<>();
        index = 0;
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        // return buildTree(preorder, 0, preorder.length - 1);
        return buildTree(preorder, 0, preorder.length - 1, 0, inorder.length - 1);
    }

    // ok
    private TreeNode buildTree(int[] preorder, int left, int right) {
        if (left > right) {
            return null;
        }
        int val = preorder[index++];
        TreeNode root = new TreeNode(val);
        root.left = buildTree(preorder, left, inorderMap.get(val) - 1);
        root.right = buildTree(preorder, inorderMap.get(val) + 1, right);
        return root;
    }

    // 对于前序遍历：中 左 右  左子树 中->左
    //    中序遍历：左 中 右  左子树 左->中
    private TreeNode buildTree(int[] preorder, int preStart, int preEnd, int inStart, int inEnd) {
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }
        int val = preorder[preStart];
        int inorderIdx = inorderMap.get(val);// 这个就是中序遍历的 中
        int leftSize = inorderIdx - inStart;
        TreeNode root = new TreeNode(val);
        //                                      中（不包含）              左                    左              中（不包含）
        root.left = buildTree(preorder, preStart + 1, preStart + leftSize, inStart, inorderIdx - 1);
        root.right = buildTree(preorder, preStart + leftSize + 1, preEnd, inorderIdx + 1, inEnd);
        return root;
    }


    // N0106 inorder   left  mid   right
    //       postorder left right  mid
    private TreeNode buildTree2(int[] postOrder, int postStart, int postEnd, int inStart, int inEnd) {
        if (postStart > postEnd || inStart > inEnd) {
            return null;
        }
        int val = postOrder[postEnd];
        int inorderIdx = inorderMap.get(val);
        int leftSize = inorderIdx - inStart;
        TreeNode root = new TreeNode(val);
        //                                left                right(不包含)             left           mid（不包含）
        root.left = buildTree2(postOrder, postStart, postStart + leftSize - 1, inStart, inorderIdx - 1);
        //                                right                                   mid(不包含)          mid(不包含)     right
        root.right = buildTree2(postOrder, postStart + leftSize, postEnd - 1, inorderIdx + 1, inEnd);
        return root;
    }


    // N0889
    Map<Integer, Integer> postMap;

    public TreeNode constructFromPrePost1(int[] pre, int[] post) {
        postMap = new HashMap<>();
        for (int i = 0; i < post.length; i++) {
            postMap.put(post[i], i);
        }
        return dfs(pre, 0, pre.length - 1, 0, post.length - 1);
    }

    // 前序遍历：中 左 右
    // 后序遍历：左 右 中 optimize 分而治之
    private TreeNode dfs(int[] pre, int preStart, int preEnd, int postStart, int postEnd) {
        if (preStart > preEnd || postStart > postEnd) {
            return null;
        }
        TreeNode root = new TreeNode(pre[preStart]);
        if (preStart + 1 <= preEnd) {
            // 前序遍历左子节点索引 - 后序遍历左子节点索引 = 左子树高度
            int deltaIndex = postMap.get(pre[preStart + 1]) - postStart + 1;
            //   左子树                     中（不包含）            左                      左                 右（不包含）
            root.left = dfs(pre, preStart + 1, preStart + deltaIndex, postStart, postStart + deltaIndex - 1);
            //   右子树                     左（不包含）                  右                右                                中（不包含）
            root.right = dfs(pre, preStart + deltaIndex + 1, preEnd, postStart + deltaIndex, postEnd - 1);
        }
        return root;
    }

    int preIdx = 0;

    int postIdx = 0;

    public TreeNode constructFromPrePost2(int[] pre, int[] post) {
        preIdx = 0;
        postIdx = 0;
        return constructFromPrePost21(pre, post);
    }

    // optimize 后序遍历
    public TreeNode constructFromPrePost21(int[] pre, int[] post) {
        // 首先根据前序遍历构建节点
        TreeNode root = new TreeNode(pre[preIdx++]);
        // 当我们遇到一个节点值pre[i]等于当前的时post[j]，这意味着我们已经完成了 的子树的构建pre[i]。
        // 所以我们不应该继续向该子树添加子节点。相反，我们应该弹出该子树并继续到可以添加子节点的路径。
        if (post[postIdx] != root.val) {
            root.left = constructFromPrePost21(pre, post);
        }
        if (post[postIdx] != root.val) {
            root.right = constructFromPrePost21(pre, post);
        }
        postIdx++;
        return root;
    }
}
