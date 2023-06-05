package com.icboluo.leetcode.tree.前缀树;

/**
 * @author icboluo
 * @since 2023-06-05 21:25
 */
class N0211_添加与搜索单词 {
    Node root;

    public N0211_添加与搜索单词() {
        root = new Node();
    }

    // 似乎我们应该将树的操作移动到节点中，这样方便方法的通用性
    public void addWord(String word) {
        Node temp = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (temp.child[ch - 'a'] == null) {
                temp.child[ch - 'a'] = new Node();
            }
            temp = temp.child[ch - 'a'];
        }
        temp.isWord = true;
    }

    public boolean search(String word) {
        return match(word.toCharArray(), 0, root);
    }

    // 这个函数的定义是复杂的，难以书写的
    private boolean match(char[] chars, int idx, Node node) {
        if (idx == chars.length) {
            return node.isWord;
        }
        // 小数点代表匹配任意一个字符，如果是小数点，for循环判断child就可以了，否则必须当前匹配，并且下一个匹配
        if (chars[idx] == '.') {
            for (int i = 0; i < node.child.length; i++) {
                if (node.child[i] != null && match(chars, idx + 1, node.child[i])) {
                    return true;
                }
            }
        } else {
            return node.child[chars[idx] - 'a'] != null && match(chars, idx + 1, node.child[chars[idx] - 'a']);
        }
        return false;
    }

    static class Node {
        boolean isWord;

        // 当前节点的子节点，因为子节点可控，所以使用数组就够了
        Node[] child = new Node[26];
    }
}
