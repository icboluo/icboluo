package com.icboluo.leetcode.after_1300;

import java.util.HashSet;
import java.util.Set;

/**
 * @author icboluo
 * @since 2023-01-07 16:09
 */
class N1496_路径交叉口 {
    /**
     * 走过的路径是否交叉
     *
     * @param path
     * @return
     */
    @SuppressWarnings("all")
    public boolean isPathCrossing(String path) {
        int x = 0;
        int y = 0;
        Set<String> set = new HashSet<>();
        set.add(x + "&" + y);
        for (int i = 0; i < path.length(); i++) {
            switch (path.charAt(i)) {
                case 'N' -> x++;
                case 'S' -> x--;
                case 'E' -> y++;
                case 'W' -> y--;
            }
            if (set.contains(x + "&" + y)) {
                return true;
            }
            set.add(x + "&" + y);
        }
        return false;
    }
}
