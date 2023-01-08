package com.icboluo.leetcode.after_0000;

import java.util.Arrays;

/**
 * @author icboluo
 * @since 2022-11-25 16:08
 */
class N0014_最长公共前缀 {
    public static void main(String[] args) {
        N0014_最长公共前缀 cla = new N0014_最长公共前缀();
        String s = cla.longestCommonPrefix6(new String[]{"flower", "flow", "flight"});
        System.out.println("s = " + s);
    }

    public String longestCommonPrefix1(String[] strs) {
        int minLength = Integer.MAX_VALUE;
        for (String str : strs) {
            minLength = Math.min(minLength, str.length());
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < minLength; i++) {
            char temp = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (temp != strs[j].charAt(i)) {
                    return sb.toString();
                }
            }
            sb.append(temp);
        }
        return sb.toString();
    }


    /**
     * 方法1：挨个判断，返回bool标记，用sort做处理，就仅仅需要判断首尾单词了
     *
     * @param arr
     * @return
     */
    public String longestCommonPrefix2(String[] arr) {
        Arrays.sort(arr);
        String left = arr[0];
        String right = arr[arr.length - 1];
        int count = 0;
        for (int i = 0; i < left.length(); i++) {
            if (left.charAt(i) == right.charAt(i)) {
                count++;
            } else {
                break;
            }
        }
        return left.substring(0, count);
    }

    /**
     * 方法2：水平比对：如果其他的字符串任意一个不能满足全部startWith第一个，让第一个变短，直到满足或者第一个字符串变为空为止
     *
     * @param arr
     * @return
     */
    public String longestCommonPrefix3(String[] arr) {
        Arrays.sort(arr);
        String str = arr[0];
        for (String item : arr) {
            while (!item.startsWith(str)) {
                str = str.substring(0, str.length() - 1);
            }
        }
        return str;
    }

    /**
     * 方法3：垂直比对：一个元素一个元素比较
     *
     * @param arr
     * @return
     */
    public String longestCommonPrefix4(String[] arr) {
        Arrays.sort(arr);
        for (int i = 0; i < arr[0].length(); i++) {
            char c = arr[0].charAt(i);
            for (String item : arr) {
                if (i == item.length() || item.charAt(i) != c) {
                    return arr[0].substring(0, i);
                }
            }
        }
        return arr[0];
    }

    /**
     * 方法4：分治算法 FIXME ERROR 栈溢出了，也看不懂这个写法
     *
     * @param arr
     * @return
     */
    public String longestCommonPrefix5(String[] arr) {
        return sub5(arr, 0, arr.length - 1);
    }

    private String sub5(String[] arr, int l, int r) {
        if (l == r) {
            return arr[l];
        }
        int mid = l + (r - l) >> 1;
        String lStr = sub5(arr, l, mid);
        String rStr = sub5(arr, mid + 1, r);
        return sub5(lStr, rStr);
    }

    /**
     * 2个字符串的公共前缀
     *
     * @param lStr
     * @param rStr
     * @return
     */
    private String sub5(String lStr, String rStr) {
        int min = Math.min(lStr.length(), rStr.length());
        for (int i = 0; i < min; i++) {
            if (lStr.charAt(i) != rStr.charAt(i)) {
                return lStr.substring(0, i);
            }
        }
        return lStr.substring(0, min);
    }

    /**
     * 方法5：二分查找 FIXME ERROR
     *
     * @param arr
     * @return
     */
    public String longestCommonPrefix6(String[] arr) {
        Arrays.sort(arr);
        int minLen = Arrays.stream(arr).mapToInt(String::length).min().getAsInt();
        int low = 1;
        int high = minLen;
        while (low <= high) {
            int mid = low + (high - low) >> 1;
            if (sub6(arr, mid)) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return arr[0].substring(0, (low + high) >> 1);
    }

    /**
     * 数组从0-mid是否拥有公共前缀
     *
     * @param arr
     * @param mid
     * @return
     */
    private boolean sub6(String[] arr, int mid) {
        String temp = arr[0].substring(0, mid);
        for (String item : arr) {
            if (!item.startsWith(temp)) {
                return false;
            }
        }
        return true;
    }
}
