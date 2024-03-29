package com.icboluo.leetcode.tree.多叉树建模;

import java.util.*;

/**
 * @author icboluo
 * @since 2022-11-01 13:44
 */
public class N1600_王位继承顺序 {

    String root;
    /**
     * 多叉树问题
     */
    Map<String, List<String>> map = new HashMap<>();

    Set<String> set = new HashSet<>();

    public N1600_王位继承顺序(String kingName) {
        root = kingName;
    }

    public void birth(String parentName, String childName) {
        map.computeIfAbsent(parentName, key -> new ArrayList<>()).add(childName);
    }

    public void death(String name) {
        set.add(name);
    }

    public List<String> getInheritanceOrder() {
        List<String> res = new ArrayList<>();
        preorder(root, res);
        return res;
    }

    private void preorder(String root, List<String> list) {
        if (!set.contains(root)) {
            list.add(root);
        }
        if (map.containsKey(root)) {
            for (String child : map.get(root)) {
                preorder(child, list);
            }
        }
    }
}
