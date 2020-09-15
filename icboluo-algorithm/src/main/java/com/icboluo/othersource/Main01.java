package com.icboluo.othersource;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main01 {
    private static ArrayList<Integer> temp;
    private static int c;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        String[] split = str.split(" ");
        ArrayList<Integer> list = new ArrayList<>();
        for (String s : split) {
            list.add(Integer.parseInt(s));
        }
//6 1 2 3
        abc(list);
        for (int i = temp.size() - 1; i >= 0; i--) {
            System.out.print(temp.get(i)+" ");
        }
    }

    private static void abc(ArrayList<Integer> list) {
        temp = list;
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i; j < list.size() - 1; j++) {
                int sum = sum(i, j, list);
                if (sum == list.get(j + 1)) {
                    temp = list;
                    gaibianlist(i, j);
                    abc(temp);
                }
            }
        }
    }

    private static void gaibianlist(int i, int j) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int a = 0; a < temp.size(); a++) {
            if (a >= i && a <= j) {
            } else if (a == j + 1) {
                list.add(temp.get(j + 1) * 2);
            } else {
                list.add(temp.get(a));
            }
        }
        temp = list;
    }

    private static int sum(int i, int j, ArrayList<Integer> list) {
        int sum = 0;
        for (int i1 = 0; i1 < list.size(); i1++) {
            if (i1 >= i && i1 <= j) {
                sum += list.get(i1);
            }
        }
        return sum;
    }
}
