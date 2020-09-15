package com.icboluo.datastructure.tree.binary;

import lombok.Setter;

/**
 * 二叉树
 *
 * @author icboluo
 * @date 2020/6/14 16:43
 */
 class BinaryTree {
    @Setter
    private HeroNode root;

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
