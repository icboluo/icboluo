package com.icboluo.leetcode.tree.前缀树;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author icboluo
 * @since 2023-06-05 23:35
 */
class N0212_单词查找 {
    // FIXME
    public static void main(String[] args) {
        var cla = new N0212_单词查找();
        @SuppressWarnings("all")
        char[][] arr = {// ------------------------------
                {'o', 'a', 'a', 'n'},// -----------------
                {'e', 't', 'a', 'e'},// -----------------
                {'i', 'h', 'k', 'r'},// -----------------
                {'i', 'f', 'l', 'v'}};
        System.out.println(cla.findWords(arr, new String[]{"oath", "pea", "eat", "rain"}));
    }

    Set<String> res;

    // 很清晰的基础trie和dfs，但是书写并不简单
    public List<String> findWords(char[][] board, String[] words) {
        res = new HashSet<>();
        Trie root = new Trie();
        for (String word : words) {
            root.insert(word);
        }
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                dfs(board, visited, i, j, root, "");
            }
        }
        return new ArrayList<>(res);
    }

    private void dfs(char[][] board, boolean[][] visited, int i, int j, Trie root, String str) {
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length) {
            return;
        }
        if (visited[i][j]) {
            return;
        }
        str += board[i][j];
        // 方法1，
        // if (!root.startWith(str)) {
        //     return;
        // }
        // // root中存储的就是单词，所以当前的通路走到str的时候，只要匹配单词，记录数据即可
        // if (root.search(str)) {
        //     res.add(str);
        // }
        // 方法2,这种方法好很多，简洁移动
        root = root.child[board[i][j] - 'a'];
        if (root != null && root.isWord) {
            res.add(str);
            // 将该单词标记为非单词，代表已访问，防止重复
            root.isWord = false;
        }
        visited[i][j] = true;
        dfs(board, visited, i - 1, j, root, str);
        dfs(board, visited, i + 1, j, root, str);
        dfs(board, visited, i, j - 1, root, str);
        dfs(board, visited, i, j + 1, root, str);
        visited[i][j] = false;
    }

    static class Trie {
        boolean isWord;

        Trie[] child = new Trie[26];

        public void insert(String word) {
            Trie temp = this;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (temp.child[ch - 'a'] == null) {
                    temp.child[ch - 'a'] = new Trie();
                }
                temp.child[ch - 'a'] = new Trie();
                temp = temp.child[ch - 'a'];
            }
            temp.isWord = true;
        }
    }
}
