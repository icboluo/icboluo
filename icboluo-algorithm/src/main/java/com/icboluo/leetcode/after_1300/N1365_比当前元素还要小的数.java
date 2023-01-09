package com.icboluo.leetcode.after_1300;

import java.util.*;

/**
 * @author icboluo
 * @since 2023-01-08 14:28
 */
class N1365_比当前元素还要小的数 {
    public static void main(String[] args) {
        N1365_比当前元素还要小的数 cla = new N1365_比当前元素还要小的数();
        int[] ints = cla.answerQueries(new int[]{4, 5, 2, 1}, new int[]{3, 10, 21});
        System.out.println(Arrays.toString(ints));
    }

    /**
     * 0315 数自己之后的小数，it`s hard, make merge sort can solve TODO 是值得做的一道题
     *
     * @param nums
     * @return
     */
    public List<Integer> countSmaller(int[] nums) {
        return null;
    }

    /**
     * 0826 大多数利润分配工作 FIXME ERROR
     * 每个工人只能做一份工作，一份工作可以完成多次
     *
     * @param difficulty 工作难度
     * @param profit     工作利润
     * @param worker     工人工作能力
     * @return
     */
    public int maxProfitAssignment1(int[] difficulty, int[] profit, int[] worker) {
        // 一对 整合工作难度和工作利润，可以用二维数组，也可以用内部类的方式
        int[][] pair = new int[difficulty.length][2];
        for (int i = 0; i < difficulty.length; i++) {
            pair[i][0] = difficulty[i];
            pair[i][1] = profit[i];
        }
        Arrays.sort(pair, Comparator.comparingInt(a -> a[0]));
        int i = 0;
        int best = 0;
        int res = 0;
        // 尝试对每个能力进行界定，求出其最大可能受益
        for (int ability : worker) {
            // 能力等于也是可以的
            while (i < pair.length && ability >= pair[i][0]) {
                best = Math.max(best, pair[i][1]);
                i++;
            }
            res += best;
        }
        return res;
    }

    public int maxProfitAssignment2(int[] difficulty, int[] profit, int[] worker) {
        // 直接计算好 当前工作难度的最大利润
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(0, 0);
        // 先求每个工作的利润
        for (int i = 0; i < difficulty.length; i++) {
            // 这个math.max是有必要的，工作的利润不要用较小的利润覆盖较大的利润，工作困难度是可以重复的
            map.put(difficulty[i], Math.max(profit[i], map.getOrDefault(difficulty[i], 0)));
        }
        // 再求最大利润
        int best = 0;
        // 因为是有序遍历，在迭代过程中，best的值会被不停的更新至最优
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            best = Math.max(entry.getValue(), best);
            map.put(entry.getKey(), best);
        }
        int res = 0;
        for (int ability : worker) {
            // 注意这个api
            res += map.floorEntry(ability).getValue();
        }
        return res;
    }

    /**
     * N1365_比当前元素还要小的数
     *
     * @param nums
     * @return
     */
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] arr = Arrays.copyOf(nums, nums.length);
        // 排序之后，前面的就是比当前小的了
        Arrays.sort(arr);
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            boolean isPut = false;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[i] == arr[j]) {
                    isPut = true;
                    // 比i小的有j个
                    map.put(arr[i], j);
                } else {
                    break;
                }
            }
            // i是最大的，没有比i小的
            if (!isPut) {
                map.put(arr[i], i);
            }
        }
        int[] res = new int[nums.length];
        for (int i = 0; i < res.length; i++) {
            res[i] = map.get(nums[i]);
        }
        return res;
    }

    /**
     * 1754 两个字符串的最大合并
     *
     * @param word1
     * @param word2
     * @return
     */
    public String largestMerge(String word1, String word2) {
        String res = "";
    }

    /**
     * 2300 成功的法术和药水对；2个数组相互乘积大于success的对数 FIXME ERROR
     *
     * @param spells
     * @param potions
     * @param success
     * @return
     */
    public int[] successfulPairs1(int[] spells, int[] potions, long success) {
        Arrays.sort(spells);
        // 元素出现的位置
        TreeMap<Integer, Integer> map = new TreeMap<>();
        // 这里也可以在下面 +-1来调整
        map.put(Integer.MAX_VALUE, potions.length);
        // 这里要逆序，这样可以获得元素出现的第一个位置
        for (int i = potions.length - 1; i >= 0; i--) {
            map.put(potions[i], i);
        }
        for (int i = 0; i < spells.length; i++) {
            // 需要的被除数
            long need = (success + spells[i] - 1) / spells[i];
            // 注意这个api，找出被除数的位置，后面的均是可行解
            spells[i] = potions.length - map.ceilingEntry((int) need).getValue();
        }
        return spells;
    }

    /**
     * 以计算2数之和的方式计算
     *
     * @param spells
     * @param potions
     * @param success
     * @return
     */
    public int[] successfulPairs2(int[] spells, int[] potions, long success) {
        int[] clone = spells.clone();
        Arrays.sort(spells);
        Arrays.sort(potions);
        // 元素出现的位置
        int j = potions.length - 1;
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int i = 0; i < spells.length; i++) {
            while (j >= 0 && (long) spells[i] * potions[j] >= success) {
                j--;
            }
            countMap.put(spells[i], potions.length - j - 1);
        }
        int[] res = new int[spells.length];
        for (int i = 0; i < res.length; i++) {
            res[i] = countMap.get(clone[i]);
        }
        return res;
    }

    /**
     * 2389 有限和的最长子序列
     *
     * @param nums
     * @param queries
     * @return nums中元素和小于等于queries[i]的子序列的最大长度
     */
    public int[] answerQueries(int[] nums, int[] queries) {
        Arrays.sort(nums);
        // 前缀和数组
        int[] preSum = new int[nums.length];
        preSum[0] = nums[0];
        for (int i = 1; i < preSum.length; i++) {
            preSum[i] = preSum[i - 1] + nums[i];
        }
        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            // 二分查找，在前缀和数组中找到对应的节点，个数就是索引+1
            // 这个二分查找，如果找不到会返回插入位置的索引（为负值）-1
            int j = Arrays.binarySearch(preSum, queries[i]);
            res[i] = Math.abs(j + 1);
        }
        return res;
    }

    /**
     * 2410 球员与教练的最大匹配
     * 选手最多匹配一个教练，教练最多可以匹配一个选手
     *
     * @param players  玩家能力
     * @param trainers 训练者的能力，要求大一点
     * @return 最大匹配数
     */
    public int matchPlayersAndTrainers(int[] players, int[] trainers) {
        Arrays.sort(players);
        Arrays.sort(trainers);
        int count = 0;
        // 我们可以总是取较小的玩家，较大的教练；较小的玩家是说，玩家指针慢移，教练指针快移
        int j = 0;
        for (int i = 0; i < trainers.length; i++) {
            if (j < players.length && trainers[i] >= players[j]) {
                count++;
                j++;
            }
        }
        return count;
    }
}
