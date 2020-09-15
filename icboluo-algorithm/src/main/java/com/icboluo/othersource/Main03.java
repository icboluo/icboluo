package com.icboluo.othersource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Main03 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ((str = br.readLine()) != null) {
            String[] split = str.split(",");
            ArrayList<String> list = new ArrayList<>();
            HashMap<String, Integer> map = new HashMap<>();
            for (String s : split) {
                int length = s.length();
                s += "999999";
                s = s.substring(0, 6);
                list.add(s);
                map.put(s, length);
            }
            List<String> collect = list.stream()
                    .sorted()
                    .collect(Collectors.toList());

            for (int i = collect.size() - 1; i >= 0; i--) {
                Integer val = map.get(collect.get(i));
                System.out.print(collect.get(i).substring(0, val));
            }
        }
    }
}
