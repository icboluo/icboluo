package com.icboluo.leetcode.tree.前缀树;

/**
 * @author icboluo
 * @since 2023-05-10 18:35
 */
public class N0208_实现前缀树 {
    Node root;

    // trie
    public N0208_实现前缀树() {
        root = new Node();
    }

    public void insert(String word) {
        Node temp = root;
        // 新建链式的树结构
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (temp.children[ch - 'a'] == null) {
                temp.children[ch - 'a'] = new Node();
            }
            temp = temp.children[ch - 'a'];
        }
        // 在最终的节点上标记属于一个单词
        temp.isWord = true;
    }

    public boolean search(String word) {
        Node temp = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            // 如果中途停止，说明source的单词过短
            if (temp.children[ch - 'a'] == null) {
                return false;
            }
            temp = temp.children[ch - 'a'];
        }
        // 如果没有及时停止，说明source的单词过长
        return temp.isWord;
    }

    public boolean startsWith(String prefix) {
        Node temp = root;
        // 和上面的代码一模一样
        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            // 如果中途停止，说明source的单词过短
            if (temp.children[ch - 'a'] == null) {
                return false;
            }
            temp = temp.children[ch - 'a'];
        }
        return true;
    }

    class Node {
        boolean isWord;

        Node[] children = new Node[26];
    }
}
