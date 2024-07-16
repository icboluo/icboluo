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

    // N0889 FIXME
    // preorder  mid  left  right
    // postorder left right mid
    private TreeNode buildTree3(int[] preorder, int preStart, int preEnd, int inStart, int inEnd) {
        if (preStart > preEnd) {
            return null;
        }
        int val = preorder[preStart];
        int inorderIdx = inorderMap.get(val);
        int leftSize = inorderIdx - inStart;
        TreeNode root = new TreeNode(val);
        root.left = buildTree3(preorder, preStart, preStart + leftSize - 1, inStart, inorderIdx - 1);
        root.right = buildTree3(preorder, preStart + leftSize + 1, preEnd - 1, inorderIdx + 1, inEnd);
        return root;
    }
}
