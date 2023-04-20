package com.icboluo.leetcode.after_2000;

/**
 * @author icboluo
 * @since 2023-04-20 23:29
 */
class N2446_判断2个事件是否有冲突 {
    /**
     * 2个时间是否有交集
     *
     * @param event1
     * @param event2
     * @return
     */
    public boolean haveConflict(String[] event1, String[] event2) {
        // 我们期望前面的小，后面的大
        if (event1[0].compareTo(event2[0]) > 0) {
            return haveConflict(event2, event1);
        }
        String preEnd = event1[1];
        String curStart = event2[0];
        return preEnd.compareTo(curStart) >= 0;
    }
}
