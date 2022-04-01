package com.icboluo.othersource;

import java.util.Scanner;

public class N0001_最少车辆数 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String[] strArr = str.split(",");
        int[] arr = new int[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
//            什么测试用例、、、
            if (strArr[i] != null && !"null".equals(strArr[i])) {
                arr[i] = Integer.parseInt(strArr[i]);
            }
        }
        int res = fun(arr);
        System.out.println(res);
    }

    /**
     * 最少车辆数
     *
     * @param arr 车辆数组
     * @return
     */
    private static int fun(int[] arr) {
        int curLength = 0;
        int total = 0;
        for (int i = 0; i < arr.length; i++) {
//            TODO 这块条件判断可以用arr为主导，修改下，考试追求效率
//            跳过条件
            if (curLength == 0 && arr[i] == 0) {
                continue;
            }
            if (curLength != 0 && arr[i] == 0) {
                total++;
                curLength = 0;
            }
            if (arr[i] == 1) {
                //            循环变量
                curLength++;
            }
//            终止条件
            if (curLength == 3) {
                curLength = 0;
                total++;
            }
        }
//        尾数收集
        if (curLength != 0) {
            total++;
        }
        return total;
    }
}
