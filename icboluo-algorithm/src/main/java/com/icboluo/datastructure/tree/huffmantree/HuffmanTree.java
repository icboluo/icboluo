package com.icboluo.datastructure.tree.huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class HuffmanTree {
   public static void main(String[] args) {
       int[] arr = {13, 7, 8, 3, 29, 6, 1};
       Node node = creatHuffmanTree(arr);
       preOrder(node);
   }

   /**
    * 创建赫夫曼树
    *
    * @param arr 任意数组
    * @return 赫夫曼树的根结点
    */
   public static Node creatHuffmanTree(int[] arr) {
       List<Node> nodes = new ArrayList<>();
       for (int value : arr) {
           nodes.add(new Node(value));
       }
       while (nodes.size() > 1) {
           Collections.sort(nodes);
           Node left = nodes.get(0);
           Node right = nodes.get(1);
           Node mid = new Node(left.value + right.value);
           mid.left = left;
           mid.right = right;
           nodes.remove(left);
           nodes.remove(right);
           nodes.add(mid);
       }
       return nodes.get(0);
   }

   public static void preOrder(Node root) {
       if (root != null) {
           root.preOrder();
       } else {
           System.out.println("空树不能遍历");
       }
   }
}
