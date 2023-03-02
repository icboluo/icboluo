package com.icboluo.algorithm;

import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * 贪心算法
 *
 * @author icboluo
 * @since 2020-08-05 16:19
 */
class GreedyAlgorithm {
    public static void main(String[] args) {
        // 广播信息
        Map<String, Set<String>> broadcasts = new HashMap<>();

        broadcasts.put("k1", Set.of("北京", "上海", "天津"));
        broadcasts.put("k2", Set.of("广州", "上海", "深圳"));
        broadcasts.put("k3", Set.of("成都", "上海", "杭州"));
        broadcasts.put("k4", Set.of("上海", "天津"));
        broadcasts.put("k5", Set.of("杭州", "大连"));

        HashSet<String> allAreas = new HashSet<>();
        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("天津");
        allAreas.add("广州");
        allAreas.add("深圳");
        allAreas.add("成都");
        allAreas.add("杭州");
        allAreas.add("大连");
        // 最后选择的广播
        List<String> selects = new ArrayList<>();
        Set<String> tempSet = new HashSet<>();
        while (!CollectionUtils.isEmpty(allAreas)) {
            String maxKey = null;
            for (Map.Entry<String, Set<String>> entry : broadcasts.entrySet()) {
                tempSet.addAll(entry.getValue());
                tempSet.retainAll(allAreas);
                if (!tempSet.isEmpty() && (maxKey == null || tempSet.size() > broadcasts.get(maxKey).size())) {
                    maxKey = entry.getKey();
                }
                // 这样可以反复地使用同一个容器，不必重新创建
                tempSet.clear();
            }
            if (maxKey != null) {
                selects.add(maxKey);
                allAreas.removeAll(broadcasts.get(maxKey));
            }
        }
        System.out.println(selects);
    }
}
