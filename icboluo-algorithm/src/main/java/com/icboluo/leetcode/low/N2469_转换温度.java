package com.icboluo.leetcode.low;

/**
 * @author icboluo
 * @since 2023-04-20 23:47
 */
class N2469_转换温度 {
    public double[] convertTemperature(double celsius) {
        double[] arr = new double[2];
        arr[0] = celsius + 273.15;
        arr[1] = celsius * 1.80 + 32.00;
        return arr;
    }
}
