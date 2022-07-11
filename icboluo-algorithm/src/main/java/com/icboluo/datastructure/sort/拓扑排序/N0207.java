package com.icboluo.datastructure.sort.拓扑排序;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author icboluo
 * @since 2022-07-10 12:27
 */
public class N0207 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] prerequisite : prerequisites) {
            map.computeIfAbsent(prerequisite[1], key -> new ArrayList<>()).add(prerequisite[0]);
        }
        return false;
    }
}
