package com.icboluo.leetcode.after_1300;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author icboluo
 * @since 2022-05-29 16:25
 */
class N1376_通知最短时间 {
    /**
     * 经理下属关系
     */
    Map<Integer, List<Integer>> idxMap;

    int[] informTime;

    /**
     * @param n
     * @param headID
     * @param manager    i的经理
     * @param informTime 通知时间
     * @return
     */
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        idxMap = new HashMap<>();
        for (int i = 0; i < manager.length; i++) {
            int father = manager[i];
            idxMap.getOrDefault(father, new ArrayList<>()).add(i);
        }
        this.informTime = informTime;
        return dfs(headID);
    }

    private int dfs(int cur) {
        if (!idxMap.containsKey(cur)) {
            return 0;
        }
        List<Integer> subList = idxMap.get(cur);
        int subMax = 0;
        for (Integer sub : subList) {
            int subTime = dfs(sub);
            subMax = Math.max(subMax, subTime);
        }
        return subMax + informTime[cur];
    }
}
