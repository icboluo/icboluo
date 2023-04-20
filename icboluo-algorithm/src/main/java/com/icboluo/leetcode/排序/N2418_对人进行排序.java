package com.icboluo.leetcode.排序;

import java.util.stream.IntStream;

/**
 * @author icboluo
 * @since 2023-04-20 23:08
 */
class N2418_对人进行排序 {
        /**
         * 按身高降序排列名称
         * 方案2：可以利用treeMap中entry存储映射关系，key做大小排序
         *
         * @param names
         * @param heights
         * @return
         */
        public String[] sortPeople(String[] names, int[] heights) {
            return IntStream.range(0, names.length)
                    .boxed()
                    .sorted((a, b) -> heights[b] - heights[a])
                    .map(i -> names[i])
                    .toArray(String[]::new);
        }
}
