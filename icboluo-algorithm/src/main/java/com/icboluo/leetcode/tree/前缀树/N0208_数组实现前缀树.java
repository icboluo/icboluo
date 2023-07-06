package com.icboluo.leetcode.tree.前缀树;

/**
 * @author icboluo
 * @since 2023-05-10 18:35
 */
public class N0208_数组实现前缀树 {
    Node root;

    // trie
    public N0208_数组实现前缀树() {
        root = new Node();
    }

    public void insert(String word) {
        root.insert(word);
    }

    public boolean search(String word) {
        return root.search(word);
    }

    public boolean startsWith(String prefix) {
        return root.startsWith(prefix);
    }

    private static class Node {
        Node[] children = new Node[26];
        boolean isEnd;

        public void insert(String word) {
            Node temp = this;
            // 新建链式的树结构
            for (char ch : word.toCharArray()) {
                if (temp.children[ch - 'a'] == null) {
                    temp.children[ch - 'a'] = new Node();
                }
                temp = temp.children[ch - 'a'];
            }
            // 在最终的节点上标记属于一个单词
            temp.isEnd = true;
        }

        public boolean search(String word) {
            Node temp = this;
            for (char ch : word.toCharArray()) {
                // 如果中途停止，说明source的单词过短
                if (temp.children[ch - 'a'] == null) {
                    return false;
                }
                temp = temp.children[ch - 'a'];
            }
            // 如果没有及时停止，说明source的单词过长
            return temp.isEnd;
        }

        public boolean startsWith(String prefix) {
            Node temp = this;
            // 和上面的代码一模一样
            for (char ch : prefix.toCharArray()) {
                // 如果中途停止，说明source的单词过短
                if (temp.children[ch - 'a'] == null) {
                    return false;
                }
                temp = temp.children[ch - 'a'];
            }
            return true;
        }
    }
}
