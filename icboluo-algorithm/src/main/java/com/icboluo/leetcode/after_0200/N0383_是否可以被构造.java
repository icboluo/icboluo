package com.icboluo.leetcode.after_0200;

import java.util.HashMap;
import java.util.Map;

/**
 * @author icboluo
 * @since 2022-11-26 11:42
 */
class N0383_是否可以被构造 {
    public boolean canConstruct(String ransomNote, String magazine) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < magazine.length(); i++) {
            map.put(magazine.charAt(i), map.getOrDefault(magazine.charAt(i), 0) + 1);
        }
        for (int i = 0; i < ransomNote.length(); i++) {
            char ch = ransomNote.charAt(i);
            if (map.containsKey(ch) && map.get(ch) > 0) {
                map.put(ch, map.get(ch) - 1);
            }else{
                return false;
            }
        }
        return true;
    }
}
