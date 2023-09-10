package com.icboluo.leetcode.after_0000;

/**
 * @author icboluo
 * @since 2023-09-10 18:55
 */
class N0134_0149_加油站 {
    /**
     * 此块代码是巧妙的，难以完成的
     *
     * @param gas  1,2,3,4,5
     * @param cost 3,4,5,1,2
     * @return
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
// sum从头到尾累计，用于判断整体数据是否合法
        int sum = 0;
        // temp用于确定start的位置
        int temp = 0;
        int start = 0;
        for (int i = 0; i < gas.length; i++) {
            sum += gas[i] - cost[i];
            temp += gas[i] - cost[i];
            // 第一个累计为负值的地方，说明当前数据是不合理的，从下一个节点算为开始节点
            if (temp < 0) {
                temp = 0;
                start = i + 1;
            }
        }
        // 如果总的花费较高，说明不可以完成，如果总的花费较低，说明一定能完成（证明略
        return (sum < 0) ? -1 : start;
    }

    // 0146 TODO


    /**
     * 0149 直线上的最大数
     * 总的策略是给定点坐标（一般是x坐标固定位1），计算斜率，只要满足定点坐标相等，斜率相等，就是一条直线的；需要双层for循环两两计算，半数学问题，暂不处理
     *
     * @param points
     * @return
     */
    public int maxPoints(int[][] points) {
        return -1;
    }
}
