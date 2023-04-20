package com.icboluo.leetcode.after_2000;

import java.util.HashMap;

/**
 * @author icboluo
 * @since 2023-04-20 22:40
 */
class N2325_解码信息 {
    /**
     * @param key     和26个字母位置对应
     * @param message 需要解码的信息
     * @return
     */
    public String decodeMessage(String key, String message) {
        // 首先，我们需要 字符->26个字母的映射，所以用数组不合适（数组是规整->其他
        HashMap<Character, Character> map = new HashMap<>();
        char val = 'a';
        for (int i = 0; i < key.length(); i++) {
            if (key.charAt(i) == ' ') {
                continue;
            }
            if (!map.containsKey(key.charAt(i))) {
                map.put(key.charAt(i), val++);
            }
        }
        String res = "";
        for (int i = 0; i < message.length(); i++) {
            if (message.charAt(i) == ' ') {
                res += ' ';
            } else {
                res += map.get(message.charAt(i));
            }
        }
        return res;
    }
}
