package com.icboluo.leetcode.前缀和_差分数组_联合查找;

import java.util.*;

/**
 * @author icboluo
 * @since 2024-06-27 下午11:45
 */
class N0990_1202_联合查找 {
    public static void main(String[] args) {
        var cla = new N0990_1202_联合查找();
        System.out.println(cla.equationsPossible2(new String[]{"a==b", "b!=a"}));
        System.out.println(cla.equationsPossible2(new String[]{"a==b", "b==a"}));
        System.out.println(cla.equationsPossible2(new String[]{"a==b", "a==c", "b==c"}));
        System.out.println(
                cla.smallestStringWithSwaps("dcab", Arrays.asList(Arrays.asList(0, 3), Arrays.asList(1, 2))));
        System.out.println(cla.smallestStringWithSwaps("dcab",
                Arrays.asList(Arrays.asList(0, 3), Arrays.asList(1, 2), Arrays.asList(0, 2))));
        System.out.println(cla.smallestStringWithSwaps("cab", Arrays.asList(Arrays.asList(0, 1), Arrays.asList(1, 2))));
    }

    // 0990 联合查找 FIXME ["a==b","e==c","b==c","a!=e"]
    public boolean equationsPossible(String[] equations) {
        // 这里不建议使用map，因为是双向的
        HashMap<Character, Character> eq = new HashMap<>();
        for (String equation : equations) {
            if (equation.charAt(1) == '=') {
                eq.put(equation.charAt(0), eq.getOrDefault(equation.charAt(3), equation.charAt(3)));
            }
        }

        for (String equation : equations) {
            if (equation.charAt(1) == '!') {
                if (eq.getOrDefault(equation.charAt(0), equation.charAt(0))
                        .equals(eq.getOrDefault(equation.charAt(3), equation.charAt(3)))) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean equationsPossible2(String[] equations) {
        UnionFind uf = new UnionFind(26);
        for (String equation : equations) {
            if (equation.charAt(1) == '=') {
                uf.union(equation.charAt(0) - 'a', equation.charAt(3) - 'a');
            }
        }
        for (String equation : equations) {
            if (equation.charAt(1) == '!' && uf.find(equation.charAt(0) - 'a') == uf.find(equation.charAt(3) - 'a')) {
                return false;
            }
        }
        return true;
    }

    // 1202 交换字符串中的元素
    // 核心思想，如果 0 1 可以交换，1 2 可以交换，则0 1 2 任意2个可以交换
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        int n = s.length();
        UnionFind uf = new UnionFind(n);
        for (List<Integer> pair : pairs) {
            uf.union(pair.get(0), pair.get(1));
        }
        Map<Integer, Queue<Character>> group = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            group.computeIfAbsent(uf.find(i), k -> new PriorityQueue<>()).add(s.charAt(i));
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(group.get(uf.find(i)).poll());
        }
        return sb.toString();
    }

    class UnionFind {
        private final int[] parent;

        private final int[] rank;

        private int count;

        public UnionFind(int n) {
            count = n;
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        /**
         * 查找根节点
         *
         * @param x
         * @return
         */
        public int find(int x) {
            // 如果元素x的父节点不是自己，那么将x的父节点设置为x的父节点的父节点。这是路径压缩的过程，可以减少查找时间
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        /**
         * 联合
         *
         * @param x
         * @param y
         */
        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return;
            }
            count--;
            // 基于size进行优化，将小树安到大树上
            // 基于rank进行优化，将矮树安到高树上（目前是这种方式
            if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
        }
    }
}

