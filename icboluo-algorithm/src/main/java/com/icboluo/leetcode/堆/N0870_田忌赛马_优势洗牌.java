package com.icboluo.leetcode.堆;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author icboluo
 * @since 2023-09-15 18:52
 */
class N0870_田忌赛马_优势洗牌 {
    // 数字序列比大小，田忌赛马
    // 思路 通过 贪心算法。来找到A可能赢B的最大分数。 这个贪心算法的思路是,首先对A和B两个序列分别进行排序,然后从最快的马开始比较。
    // 如果田忌最快的马速度大于齐王最快的马速 度,那么田忌胜利,得分加1,然后将田忌最快的马和齐王最快的马都从序列中移除。
    // 如果田忌最快的马速度小于齐王最快的马速度,那 么田忌失败,得分减1,此时为了保留田忌最快的马,我们应该用田忌最慢的马去消耗掉齐王最快的马,然后将田忌最慢的马和齐王最快 的马都从序列中移除
    // 如果田忌最快的马和齐王最快的马速度相同,此时如果平局的话,则会让田忌损失最快的马,因此我们应该找到田 忌最慢的马,即田忌必输的马来消耗掉齐王最快的马,然后将田忌最慢的马和齐王最快的马都从序列中移除
    // 相等的处理逻辑是这样的：不处理就是0分，如果用下等马怼掉对面上等马，那最差也是0分，有可能会得分，所以贪心一点就行了
    // 跑的过就跑，跑不过就用自己的下等马和对面的上等马换，这个是核心思想
    // 870,要求arr2顺序不变，求arr1比arr2大
    public int[] advantageCount(int[] arr1, int[] arr2) {
        Arrays.sort(arr1);
        int[] res = new int[arr1.length];
        // 存储arr2元素和索引的数组,元素降序；对于双数组，可以考虑将不变的做成优先级队列
        // ***** 为什么要使用pq：要求最终的结构中保留原始的顺序，但是数组的顺序又要进行变化，所以，使用pq记录下原始的顺序，并进行排序
        // pq记录数组的原因，就是原本的数组要求排序，但是又要记录原始的顺序，出现矛盾的时候用这个
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        for (int i = 0; i < arr1.length; i++) {
            pq.add(new int[]{i, arr2[i]});
        }
        int left = 0;
        int right = arr1.length - 1;
        while (!pq.isEmpty()) {
            int[] poll = pq.poll();
            int idx = poll[0];
            int val = poll[1];
            // 如果最大值 a>b,取最大值，跑得过就跑
            if (arr1[right] > val) {
                res[idx] = arr1[right--];
                // 要不然取最小值，跑不过就上下等马
            } else {
                res[idx] = arr1[left++];
            }
        }
        return res;
    }

    // a比b大的最大得分，只增不减
    private static void aLargeThanB(Integer[] arr1, Integer[] arr2) {
        Arrays.sort(arr1, (a, b) -> b - a);
        Arrays.sort(arr2, (a, b) -> b - a);
        int count = 0;
        int j = 0;
        // 要求a比b大，所以要从最大的数算起,每次循环，都要舍弃arr2的值，直到arr2为null
        for (Integer item1 : arr1) {
            // 如果不合法,舍弃arr2
            while (j < arr2.length && item1 <= arr2[j]) {
                j++;
            }
            // 如果合法，舍弃 1和2
            if (j < arr2.length && item1 > arr2[j]) {
                j++;
                count++;
            }
            // 如果到达边界，跳出
            if (j == arr2.length) {
                break;
            }
        }
        System.out.println(count);
    }
}
