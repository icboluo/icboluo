package com.icboluo.leetcode.after_1300;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author icboluo
 * @since 2023-01-15 15:55
 */
class N1423_2091_左边一部分右边一部分的最大和 {
    /**
     * 可以从卡中获得的最大积分
     * k张牌最大和，只可以在开始或者结束位置拿牌
     * 前缀和
     * 我们只能在左边获取一部分数据，右边获取一部分数据
     *
     * @param cardPoints
     * @param k
     * @return
     */
    public int maxScore(int[] cardPoints, int k) {
        int leftSum = Arrays.stream(cardPoints).limit(k).sum();
        int max = leftSum;
        int rightSum = 0;
        for (int i = 0; i < k; i++) {
            leftSum -= cardPoints[k - i - 1];
            rightSum += cardPoints[cardPoints.length - i - 1];
            max = Math.max(max, leftSum + rightSum);
        }
        return max;
    }

    /**
     * 2091 从数组中删除最小值和最大值
     * 只能从2边删除，求最小操作次数
     *
     * @param nums
     * @return
     */
    public int minimumDeletions(int[] nums) {
        int minVal = Integer.MAX_VALUE;
        int maxVal = Integer.MIN_VALUE;
        // 最小值所在的索引
        int min = -1;
        int max = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > maxVal) {
                maxVal = nums[i];
                max = i;
            }
            if (nums[i] < minVal) {
                minVal = nums[i];
                min = i;
            }
        }
        int left = Math.min(max, min);
        int right = Math.max(max, min);
        int leftInterval = left + 1;
        int rightInterval = nums.length - right;
        int midInterval = right - left;
        // 从左到右
        int a = leftInterval + midInterval;
        int b = midInterval + rightInterval;
        int c = leftInterval + rightInterval;
        return Math.min(Math.min(a, b), c);
    }

    /**
     * 1647 使字符频率唯一的最少删除 元素出现次数均不想等即可
     *
     * @param s
     * @return
     */
    public int minDeletions(String s) {
// 其实这里使用数组更好处理一点
        Map<Character, Integer> eleCountMap = IntStream.range(0, s.length())
                .mapToObj(s::charAt)
                .collect(Collectors.groupingBy(ch -> ch, Collectors.summingInt(ch -> 1)));
        // 2种思路：1.从大到小，出现重复-1；2.大的不断的--，直到当前计数没有被用过;这里采用第二种
        int[] arr = eleCountMap.values().stream().mapToInt(Integer::intValue).toArray();
        Arrays.sort(arr);
        int count = 0;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            while (set.contains(arr[i])) {
                arr[i]--;
                count++;
            }
            if (arr[i] != 0) {
                set.add(arr[i]);
            }
        }
        return count;
    }

    /**
     * 2216 使数组美观的最小删除：美观：偶数位和它下一位不想等
     * 并没有什么办法能够证明，怎么样的删除策略是快速的;可以尝试贪心原则，删除下一个元素即可
     *
     * @param nums
     * @return
     */
    public int minDeletion(int[] nums) {
        // 即便没有证明的贪心算法，写起来依然是困难的
        Integer preEven = null;
        int count = 0;
        for (int num : nums) {
            if (preEven != null && preEven == num) {
                count++;
            } else {
                // 前面的偶数元素没有值，代表当前元素为偶数位
                if (preEven == null) {
                    preEven = num;
                } else {
                    // 当前元素为奇数位，说明一对奇偶已经完成，将偶数位置空
                    preEven = null;
                }
            }
        }
        return preEven == null ? count : count + 1;
    }

    /**
     * 2170 使阵列交替的最少操作；交替：a b c，a！=b & a=c；只可以更改元素 TODO
     * 可以得到，最终的数组只有2个元素交替，这个题属于贪心吗，好难
     *
     * @param nums
     * @return
     */
    public int minimumOperations(int[] nums) {
        return -1;
    }

    /**
     * 2423 删除字母使频率相等；删除一个字母，其余元素出现次数相等 ERROR
     *
     * @param word
     * @return
     */
    public boolean equalFrequency(String word) {
        Map<Character, Integer> eleCountMap = IntStream.range(0, word.length())
                .mapToObj(word::charAt)
                .collect(Collectors.groupingBy(ch -> ch, Collectors.summingInt(ch -> 1)));
        // 不能仅仅获取最大值1个，最大值可能有多个
        List<Integer> list = eleCountMap.values().stream().sorted().toList();
        Integer max = list.get(list.size() - 1);
        List<Integer> subList = list.stream().limit(list.size() - 1).distinct().toList();
        if (subList.size() != 1) {
            return false;
        }
        if (max - 1 == subList.get(0)) {
            return true;
        }
        // a b c 的情况是个例外，删一个还是可以的
        return list.stream().distinct().allMatch(time -> 1 == time);
    }

    // fixme error abbcc 需要删除的是最小的
    public boolean equalFrequency2(String word) {
        Map<Character, Integer> eleCountMap = IntStream.range(0, word.length())
                .mapToObj(word::charAt)
                .collect(Collectors.groupingBy(ch -> ch, Collectors.summingInt(ch -> 1)));
        Map.Entry<Character, Integer> first = eleCountMap.entrySet()
                .stream().min((a, b) -> b.getValue() - a.getValue())
                .get();
        eleCountMap.put(first.getKey(), first.getValue() - 1);
        return eleCountMap.values().stream().filter(time -> time != 0).distinct().count() <= 1;
    }
}
