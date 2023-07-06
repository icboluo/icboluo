package com.icboluo.leetcode.tree.前缀树;

import java.util.HashMap;
import java.util.Map;

public class N0208_map实现前缀树 {
    private final Node root;

    public N0208_map实现前缀树() {
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
        private final Map<Character, Node> children = new HashMap<>();
        private boolean isEnd;

        public void insert(String word) {
            Node temp = this;
            for (char ch : word.toCharArray()) {
                if (!temp.children.containsKey(ch)) {
                    temp.children.put(ch, new Node());
                }
                temp = temp.children.get(ch);
            }
            temp.isEnd = true;
        }

        public boolean search(String word) {
            Node temp = this;
            for (char ch : word.toCharArray()) {
                if (!temp.children.containsKey(ch)) {
                    return false;
                }
                temp = temp.children.get(ch);
            }
            return temp.isEnd;
        }

        public boolean startsWith(String prefix) {
            Node temp = this;
            for (char ch : prefix.toCharArray()) {
                if (!temp.children.containsKey(ch)) {
                    return false;
                }
                temp = temp.children.get(ch);
            }
            return true;
        }
    }
}
