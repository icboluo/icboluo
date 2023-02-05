package com.icboluo.leetcode.建模;

/**
 * @author icboluo
 * @since 2023-02-05 13:57
 */
class N1603_设计停车系统 {
    int[] arr;

    /**
     * @param big    大车位个数
     * @param medium 中车位个数
     * @param small  小车位个数
     */
    public N1603_设计停车系统(int big, int medium, int small) {
        arr = new int[4];
        arr[1] = big;
        arr[2] = medium;
        arr[3] = small;
    }

    /**
     * @param carType 车位类型 1 2 3
     * @return 要进入的停车场的汽车，是否有某类型的停车位
     */
    public boolean addCar(int carType) {
        if (arr[carType] > 0) {
            arr[carType]--;
            return true;
        }
        return false;
    }
}
