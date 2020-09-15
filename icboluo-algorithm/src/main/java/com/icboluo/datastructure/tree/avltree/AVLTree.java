package com.icboluo.datastructure.tree.avltree;


import lombok.Getter;

class AVLTree {
   @Getter
   private Node root;

   public void add(Node node) {
       if (root == null) {
           root = node;
       } else {
           root.add(node);
       }
   }

   public void infixOrder() {
       if (root != null) {
           root.infixOrder();
       } else {
           System.out.println("二叉排序树为空");
       }
   }

   public Node search(int value) {
       if (root == null) {
           return null;
       } else {
           return root.search(value);
       }
   }

   public Node searchParent(int value) {
       if (root == null) {
           return null;
       } else {
           return root.searchParent(value);
       }
   }

   public int delRightTreeMin(Node node) {
       Node target = node;
       while (target.left != null) {
           target = target.left;
       }
       delNode(target.value);
       return target.value;
   }

   public void delNode(int value) {
       if (root == null) {
           return;
       } else {
           Node target = search(value);
           if (target == null) {
               return;
           }
           if (root.left == null & root.right == null) {
               root = null;
               return;
           }
           Node parent = searchParent(value);
           if (target.left == null && target.right == null) {
               if (parent.left != null && parent.left.value == value) {
                   parent.left = null;
               } else if (parent.right != null && parent.right.value == value) {
                   parent.right = null;
               }
           } else if (target.left != null && target.right != null) {
               int minValue = delRightTreeMin(target.right);
               target.value = minValue;
           } else {
               if (target.left != null) {
                   if (parent != null) {
                       if (parent.left.value == value) {
                           parent.left = target.left;
                       } else {
                           parent.right = target.left;
                       }
                   } else {
                       root = target.left;
                   }
               } else {
                   if (parent != null) {
                       if (parent.left.value == value) {
                           parent.left = target.right;
                       } else {
                           parent.right = target.right;
                       }
                   } else {
                       root = target.right;
                   }
               }
           }
       }
   }
}
