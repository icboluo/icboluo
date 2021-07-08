package com.icboluo.work;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author icboluo
 * @date 2020/10/12 22:06
 */
public class T {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        Map<String, String> collect = map.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    }
}
