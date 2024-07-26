package com.icboluo.leetcode.tree;

import java.util.*;

/**
 * @author icboluo
 * @since 2024-07-26 上午8:40
 */
public class N1519_子树中标签相同的节点数 {
    int[] ans;

    Map<Integer, List<Integer>> nodeMap;

    public int[] countSubTree(int n, int[][] edges, String labels) {
        nodeMap = new HashMap<>();
        // 我一直在想为什么不建一个tree，直到我看到其他人发布的测试用例[[0,2],[0,3],[1,2]]
        for (int[] edge : edges) {
            nodeMap.computeIfAbsent(edge[0], a -> new ArrayList<>()).add(edge[1]);
            nodeMap.computeIfAbsent(edge[1], a -> new ArrayList<>()).add(edge[0]);
        }
        ans = new int[n];
        dfs(0, -1, labels);
        return ans;
    }

    private char[] dfs(int node, int parent, String labels) {
        char[] count = new char[26];
        for (Integer child : nodeMap.getOrDefault(node, Collections.emptyList())) {
            // 因为是一个双向树，所以我们总是怕反着回去，判断一下即可
            if (child != parent) {
                char[] sub = dfs(child, node, labels);
                for (int i = 0; i < 26; i++) {
                    count[i] += sub[i];
                }
            }
        }
        // optimize 后序遍历
        char ch = labels.charAt(node);
        count[ch - 'a']++;
        ans[node] = count[ch - 'a'];
        return count;
    }
    //    public static int maxIdx(Class<?> cla) {
    //         toCache(cla);
    //         return CLASS_NAME_FIELD_CACHE.get(cla)
    //             .values()
    //             .stream()
    //             .map(field -> field.getAnnotation(Excel.class))
    //             .map(Excel::columnIndex)
    //             .max(Integer::compareTo)
    //             .orElse(0);
    //     }
    //
    //     public static synchronized void shoichiIndex(Field field) {
    //         if (field.isAnnotationPresent(ExcelProperty.class)) {
    //             ExcelProperty property = field.getAnnotation(ExcelProperty.class);
    //             Map<String, Object> propertyMember = getMemberValues(property);
    //             propertyMember.put("index", excel.columnIndex());
    //
    //             cleanCache.remove(Class);
    //         }
    //     }
}
