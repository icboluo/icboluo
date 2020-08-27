package com.icboluo.datastructure.tree.threadbinarytree;

import lombok.Setter;

/**
 * 实现了线索化功能的二叉树
 *
 * @author icboluo
 * @date 2020/6/14 16:43
 */
 class ThreadBinaryTree {
    @Setter
    private HeroNode root;
    /**
     * 在递归线索化时，总是保留前一个节点
     */
    private HeroNode pre = null;

    /**
     * 遍历线索化二叉树
     */
    public void threadList() {
        HeroNode node = root;
        while (node != null) {
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }
            System.out.println(node);
            while (node.getRightType() == 1) {
                node = node.getRight();
                System.out.println(node);
            }
            node = node.getRight();
        }
    }

    public void threadNodes() {
        this.threadNodes(root);
    }

    /**
     * 中序线索化二叉树
     *
     * @param node 当前要线索化的节点
     */
    public void threadNodes(HeroNode node) {
        if (node == null) {
            return;
        }
        threadNodes(node.getLeft());
        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setLeftType(1);
        }
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre = node;
        threadNodes(node.getRight());

    }

    /**
     * 前序遍历
     */
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

    public HeroNode preOrderSearch(int no) {
        if (root != null) {
            return root.preOrderSearch(no);
        } else {
            return null;
        }
    }

    public HeroNode infixOrderSearch(int no) {
        if (root != null) {
            return root.infixOrderSearch(no);
        } else {
            return null;
        }
    }

    public HeroNode postOrderSearch(int no) {
        if (root != null) {
            return root.postOrderSearch(no);
        } else {
            return null;
        }

    }

    public void delete(int no) {
        if (root != null) {
            if (root.getNo() == no) {
                root = null;
            } else {
                root.delete(no);
            }
        } else {
            System.out.println("空数不能删除");
        }

    }


}
