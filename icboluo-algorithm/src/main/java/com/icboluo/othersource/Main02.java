package com.icboluo.othersource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Main02 {
    private static int[] arr;
    private static int thisTime = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str1 = br.readLine();
        String str2 = br.readLine();

        HashMap<Integer, Integer> map = new HashMap<>();
        String[] strs1 = str1.split(" ");
        for (int i = 0; i < strs1.length; i = i + 2) {
            Integer k = Integer.parseInt(strs1[i]);
            Integer v = Integer.parseInt(strs1[i + 1]);
            map.put(k, v);
        }
        String[] strs2 = str2.split(" ");
        Integer maxLength = Integer.parseInt(strs2[0]);
        Integer peopleNum = Integer.parseInt(strs2[1]);

        arr = new int[peopleNum];

        Set<Map.Entry<Integer, Integer>> entrySet = map.entrySet();
        Set<Integer> integers = map.keySet();
        for (Map.Entry<Integer, Integer> entry : entrySet) {
            Integer time = entry.getKey();
            Integer length = entry.getValue();
            //时间后移一位
            while (thisTime < time) {
                thisTime++;
                queuejy();
            }
            //获得队列序号
            int i = getQueue();
            while (i == -1) {
                queuejy();
                thisTime++;
                i = getQueue();
            }
            custQueue(length);
        }
        int a = 0;
        if (maxLength > 1) {
            a =1;
        }
        System.out.print(maxSize() + thisTime + " " + a);
    }

    // 消费队列
    private static void custQueue(Integer length) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                arr[i] = length;
                return;
            }
        }
    }

    //对列值减一
    private static void queuejy() {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Math.max(arr[i] - 1, 0);
        }
    }

    //队列是0的时候返回
    private static int getQueue() {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                return i;
            }
        }
        return -1;
    }

    private static int maxSize() {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        return max;
    }

}
