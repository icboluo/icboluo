package com.icboluo.leetcode.元素出现次数;

/**
 * @author icboluo
 * @since 2023-02-15 23:17
 */
class N1629_按键持续时间最长的键 {
    /**
     * @param releaseTimes 升序排列，表示松开第i个键的时间
     * @param keysPressed  按的键
     * @return
     */
    public char slowestKey(int[] releaseTimes, String keysPressed) {
        int[] arr = new int[26];
        arr[keysPressed.charAt(0) - 'a'] = releaseTimes[0];
        for (int i = 1; i < releaseTimes.length; i++) {
            char ch = keysPressed.charAt(i);
            arr[ch - 'a'] = Math.max(arr[ch - 'a'], releaseTimes[i] - releaseTimes[i - 1]);
        }
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= arr[max]) {
                max = i;
            }
        }
        return (char) (max + 'a');
    }
}
