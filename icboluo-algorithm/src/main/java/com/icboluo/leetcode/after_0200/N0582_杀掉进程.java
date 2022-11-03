package com.icboluo.leetcode.after_0200;

import java.util.*;

/**
 * @author icboluo
 * @since 2022-11-03 13:05
 */
class N0582_杀掉进程 {
    // 给定一个pid和一个ppid，杀死父进程的时候会杀死子进程；给要杀死的父进程，求杀死的进程列表
    public static void main(String[] args) {
        N0582_杀掉进程 cla = new N0582_杀掉进程();
        int[] pid = {1, 3, 10, 5};
        int[] ppid = {3, 0, 5, 3};
        @SuppressWarnings("dep-ann") List<Integer> integers = cla.killProcess(pid, ppid, 5);// [5,10]
        System.out.println("integers = " + integers);
    }

    private List<Integer> killProcess(int[] pid, int[] ppid, int kill) {
        Map<Integer, List<Integer>> parentChildMap = new HashMap<>();
        for (int i = 0; i < ppid.length; i++) {
            if (!parentChildMap.containsKey(ppid[i])) {
                parentChildMap.put(ppid[i], new LinkedList<>());
            }
        }
        for (int i = 0; i < ppid.length; i++) {
            parentChildMap.get(ppid[i]).add(pid[i]);
        }
        List<Integer> ans = new ArrayList<>();
        ans.add(kill);
        kill(parentChildMap, ans, kill);
        return ans;
    }

    private void kill(Map<Integer, List<Integer>> map, List<Integer> ans, int curKill) {
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            if (entry.getKey().equals(curKill)) {
                ans.addAll(entry.getValue());
                entry.getValue().forEach(child -> kill(map, ans, child));
            }
        }
    }
}
