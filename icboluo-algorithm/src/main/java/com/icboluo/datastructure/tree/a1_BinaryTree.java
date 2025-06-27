package com.icboluo.datastructure.tree;

import com.icboluo.common.TreeNode;

/**
 * @author icboluo
 * @since 2025-06-09 21:29
 */
class a1_BinaryTree {

    public void main(String[] args) {
        TreeNode treeNode = new TreeNode(1, 2, 3, 4, 5);
        treeNode.print();

        a1_BinaryTree binaryTree = new a1_BinaryTree();
        BinaryTreeNode root1 = new BinaryTreeNode(1);
        BinaryTreeNode node2 = new BinaryTreeNode(2);
        BinaryTreeNode node3 = new BinaryTreeNode(3);
        BinaryTreeNode node4 = new BinaryTreeNode(4);
        BinaryTreeNode node5 = new BinaryTreeNode(5);

        binaryTree.root = root1;
        root1.left = node2;
        root1.right = node3;
        node3.left = node5;
        node3.right = node4;

//        TODO 这里的遍历顺序看不懂
        System.out.println("---------------PreOrder------------------------------");
        binaryTree.preOrder();
        System.out.println("---------------InfixOrder------------------------------");
        binaryTree.infixOrder();
        System.out.println("---------------PostOrder------------------------------");
        binaryTree.postOrder();

        BinaryTreeNode node = binaryTree.postOrderSearch(5);
        if (node != null) {
            System.out.println(node);
        } else {
            System.out.println("没找到");
        }
        binaryTree.delete(3);
        System.out.println("---------------After Delete PreOrder-------------------------------");
        binaryTree.preOrder();
    }

    /*
    ————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————
     */
    BinaryTreeNode root;

    //前序遍历
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空，不能遍历");
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉树为空，不能遍历");
        }
    }

    //后序遍历
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空，不能遍历");
        }
    }

    public BinaryTreeNode postOrderSearch(int no) {
        if (root != null) {
            return root.postOrderSearch(no);
        } else {
            return null;
        }

    }

    public void delete(int no) {
        if (root != null) {
            if (root.id == no) {
                root = null;
            } else {
                root.delete(no);
            }
        } else {
            System.out.println("空数不能删除");
        }

    }

    static class BinaryTreeNode {
        final int id;
        BinaryTreeNode left;
        BinaryTreeNode right;

        @Override
        public String toString() {
            return STR."BinaryTreeNode{id=\{id}}";
        }

        public BinaryTreeNode(int id) {
            this.id = id;
        }

        public void preOrder() {
            System.out.println(this);
            if (this.left != null) {
                this.left.preOrder();
            }
            if (this.right != null) {
                this.right.preOrder();
            }
        }

        public void infixOrder() {
            if (this.left != null) {
                this.left.infixOrder();
            }
            System.out.println(this);
            if (this.right != null) {
                this.right.infixOrder();
            }
        }

        public void postOrder() {
            if (this.left != null) {
                this.left.postOrder();
            }
            if (this.right != null) {
                this.right.postOrder();
            }
            System.out.println(this);
        }

        public BinaryTreeNode postOrderSearch(int no) {
            BinaryTreeNode resNode = null;
            if (this.left != null) {
                resNode = this.left.postOrderSearch(no);
            }
            if (resNode != null) {
                return resNode;
            }
            if (this.right != null) {
                resNode = this.right.postOrderSearch(no);
            }
            if (resNode != null) {
                return resNode;
            }
            if (this.id == no) {
                return this;
            }
            return resNode;
        }

        public void delete(int no) {
            if (this.left != null && this.left.id == no) {
                this.left = null;
                return;
            }
            if (this.right != null && this.right.id == no) {
                this.right = null;
                return;
            }
            if (this.left != null) {
                this.left.delete(no);
            }
            if (this.right != null) {
                this.right.delete(no);
            }
        }
    }
}
