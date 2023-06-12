package com.icboluo.leetcode.tree.前缀树;

public class Trie {
    private TrieNode root;
     public Trie() {
        root = new TrieNode();
    }
     public void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            if (node.children[c - 'a'] == null) {
                node.children[c - 'a'] = new TrieNode();
            }
            node = node.children[c - 'a'];
        }
        node.isEnd = true;
    }
     public boolean search(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            if (node.children[c - 'a'] == null) {
                return false;
            }
            node = node.children[c - 'a'];
        }
        return node.isEnd;
    }
     public boolean startsWith(String prefix) {
        TrieNode node = root;
        for (char c : prefix.toCharArray()) {
            if (node.children[c - 'a'] == null) {
                return false;
            }
            node = node.children[c - 'a'];
        }
        return true;
    }
     private class TrieNode {
        private TrieNode[] children;
        private boolean isEnd;
         public TrieNode() {
            children = new TrieNode[26];
            isEnd = false;
        }
    }
}
