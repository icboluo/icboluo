package com.icboluo.leetcode.after_1300;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author icboluo
 * @since 2023-01-07 22:08
 */
class N1436_目的地城市 {
    /**
     * 开始地-目的地，因为最终目的地只会是目的地，也就是说，最终目的地不在开始地里面
     *
     * @param paths
     * @return
     */
    public String destCity(List<List<String>> paths) {
        var startCity = paths.stream().map(li -> li.get(0)).collect(Collectors.toSet());
        for (List<String> path : paths) {
            // 目的地
            var destination = path.get(1);
            if (!startCity.contains(destination)) {
                return destination;
            }
        }
        return null;
    }
}
