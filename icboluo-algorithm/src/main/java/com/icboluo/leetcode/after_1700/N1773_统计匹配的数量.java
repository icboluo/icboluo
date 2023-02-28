package com.icboluo.leetcode.after_1700;

import lombok.AllArgsConstructor;

import java.util.List;

/**
 * @author icboluo
 * @since 2023-02-21 21:36
 */
class N1773_统计匹配的数量 {
    public int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {
        return (int) items.stream()
                .map(li -> new ShangPing(li.get(0), li.get(1), li.get(2)))
                .filter(item -> item.getRule(ruleKey).equals(ruleValue))
                .count();
    }

    @AllArgsConstructor
    class ShangPing {
        String type;

        String color;

        String name;

        public String getRule(String ruleKey) {
            return switch (ruleKey) {
                case "type" -> type;
                case "color" -> color;
                case "name" -> name;
                default -> "";
            };
        }
    }
}
