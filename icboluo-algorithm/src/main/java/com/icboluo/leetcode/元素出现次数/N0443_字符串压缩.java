package com.icboluo.leetcode.元素出现次数;

import java.util.ArrayList;
import java.util.List;

/**
 * @author icboluo
 * @since 2023-01-04 19:40
 */
class N0443_字符串压缩 {

    public static void main(String[] args) {
        N0443_字符串压缩 cla = new N0443_字符串压缩();
        int compress = cla.compress(new char[]{'a', 'a', 'b', 'b', 'c', 'c', 'c'});
        System.out.println("compress = " + compress);
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
