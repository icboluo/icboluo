package com.icboluo.leetcode.after_1000;

import java.util.*;

/**
 * @author icboluo
 * @since 2022-12-28 21:20
 */
class N1313_将数组元素按照次数展示 {
    public static void main(String[] args) {
        N1313_将数组元素按照次数展示 cla = new N1313_将数组元素按照次数展示();
        int[] res = cla.decompressRLElist(new int[]{73, 95, 5, 68, 6, 14, 98, 3, 98, 39, 100, 69, 76, 77, 93, 46, 91, 69, 26, 13, 30, 53, 15, 53, 34, 17});
        System.out.println(Arrays.toString(res));
        int compress = cla.compress(new char[]{'a', 'a', 'b', 'b', 'c', 'c', 'c'});
        System.out.println("compress = " + compress);
    }

    // FIXME ERROR
    public int[] decompressRLElist(int[] nums) {
        LinkedHashMap<Integer, Integer> eleCountMap = new LinkedHashMap<>();
        for (int i = 0; i < nums.length; i += 2) {
            eleCountMap.put(nums[i + 1], nums[i]);
        }

        return eleCountMap.entrySet().stream()
                .map(entry -> {
                    List<Integer> list = new ArrayList<>();
                    for (int i = 0; i < entry.getValue(); i++) {
                        list.add(entry.getKey());
                    }
                    return list;
                }).flatMap(Collection::stream)
                .mapToInt(Integer::intValue)
                .toArray();
    }

    /**
     * 0443 字符串压缩
     * 改成字符出现多少次的数组
     *
     * @param chars
     * @return
     */
    public int compress(char[] chars) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < chars.length; ) {
            char cur = chars[i];
            int count = 1;
            while (++i < chars.length && cur == chars[i]) {
                count++;
            }
            res.add(cur + "");
            if (count != 1) {
                String countStr = count + "";
                for (int j = 0; j < countStr.length(); j++) {
                    res.add(String.valueOf(countStr.charAt(j)));
                }
            }
        }
        // 需要把原数组也替换了
        for (int i = 0; i < res.size(); i++) {
            chars[i] = res.get(i).charAt(0);
        }
        return res.size();
    }
}
