package com.icboluo.leetcode.排序;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * @author icboluo
 * @since 2023-01-08 13:45
 */
class N0926_1653_1752_将字符串翻转为单调递增 {
    public static void main(String[] args) {
        N0926_1653_1752_将字符串翻转为单调递增 cla = new N0926_1653_1752_将字符串翻转为单调递增();
        boolean aaabbb = cla.checkString("aaabbb");
        System.out.println("aaabbb = " + aaabbb);
    }

    /**
     * 0926 将字符串翻转为单调递增 TODO 0008 这个题也是类似的
     * 前面是0，后面是1即可；翻转是说把1->0,0->1
     *
     * @param s
     * @return
     */
    public int minFlipsMonoIncr(String s) {
        int[] dp = new int[s.length() + 1];
        int count1 = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                // 这个题和下面这个题一样的，非常不好理解
                dp[i + 1] = Math.min(count1, dp[i] + 1);
            } else {
                dp[i + 1] = dp[i];
                count1++;
            }
        }
        return dp[s.length()];
    }

    /**
     * 1653 使字符串平衡的最小删除
     * 字符串仅有a，b；保障前面是a后面是b
     *
     * @param s
     * @return
     */
    public int minimumDeletions(String s) {
        // 存储最小要删除的字符数
        int[] dp = new int[s.length() + 1];
        // 已经有的b的数量
        int bCount = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'a') {
                // 这里有2种情况：1.aaaa;2.aaaabbbb
                // 前面元素为b的时候，我们需要将当前元素删除，也就是说次数等于上一个的次数+1
                // 前面元素均为a的时候，需要我们移除前面的所有b元素个数
                dp[i + 1] = Math.min(dp[i] + 1, bCount);
            } else {
                // 有点不是很好理解，如果当前是b，只要前面的字符串有效，后面补b是无所谓的，所以当前等于前面一个
                dp[i + 1] = dp[i];
                bCount++;
            }
        }
        return dp[s.length()];
    }

    /**
     * 1752 检查数组是否升序或旋转；判断数组是否是升序的，或者原本是升序的，只是经过了一次旋转
     * 比较 a,b  a>b的情况最多法师一次 FIXME ERROR
     *
     * @param nums
     * @return
     */
    public boolean check(int[] nums) {
        int k = 0;
        // 这块要考虑最后一个元素的，代码可能不对
        for (int i = 0; i < nums.length - 1; i++) {
            // 旋转最多一次
            if (nums[i] > nums[i + 1]) {
                k++;
            }
            if (k > 1) {
                return false;
            }
        }
        return true;
    }

    /**
     * 1859 排序句子；句子中有一个后置索引代表顺序，重新排序即可
     *
     * @param s
     * @return
     */
    public String sortSentence(String s) {
        String[] arr = s.split(" ");
        return Arrays.stream(arr).sorted(Comparator.comparingInt(a -> a.charAt(a.length() - 1)))
                .map(str -> str.substring(0, str.length() - 1))
                .collect(Collectors.joining(" "));
    }

    /**
     * 2042 检查句子中的数字是否升序
     *
     * @param s
     * @return
     */
    public boolean areNumbersAscending(String s) {
        String[] strArr = s.split(" ");
        int[] arr = Arrays.stream(strArr).filter(str -> Character.isDigit(str.charAt(0)))
                .mapToInt(Integer::parseInt)
                .toArray();
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i + 1] <= arr[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 2124 检查是否所有 a 都出现在所有 b 之前；字符串中仅包含a和b
     *
     * @param s
     * @return
     */
    public boolean checkString(String s) {
        if (s.isEmpty()) {
            return true;
        }
        int aLast = s.lastIndexOf("a");
        int bFirst = s.indexOf("b");
        if (aLast == -1 || bFirst == -1) {
            return true;
        }
        return aLast < bFirst;
    }
}
