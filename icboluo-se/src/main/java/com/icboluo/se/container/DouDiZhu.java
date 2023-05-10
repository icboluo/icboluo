package com.icboluo.se.container;

import java.util.*;

/**
 * @author icboluo
 * @since 2020/6/11 10:52
 */
public class DouDiZhu {

    public static void main(String[] args) {
        //准备扑克牌
        HashMap<Integer, String> pokerMap = new HashMap<>();
        List<String> colors = new ArrayList<>();
        List<String> numbers = new ArrayList<>();
        Collections.addAll(colors, "♦", "♣", "♥", "♠");
        Collections.addAll(numbers, "2", "A", "K", "Q", "J", "10", "9", "8", "7", "6", "5", "4", "3");
        int count = 1;
        pokerMap.put(count++, "大王");
        pokerMap.put(count++, "小王");
        for (String number : numbers) {
            for (String color : colors) {
                String card = color + number;
                pokerMap.put(count++, card);
            }
        }
        //洗牌
        Set<Integer> keys = pokerMap.keySet();
        List<Integer> keyList = new ArrayList<>(keys);
        Collections.shuffle(keyList);
        //发牌
        ArrayList<Integer> noP1 = new ArrayList<>();
        ArrayList<Integer> noP2 = new ArrayList<>();
        ArrayList<Integer> noP3 = new ArrayList<>();
        ArrayList<Integer> dipaiNo = new ArrayList<>();
        for (int i = 0; i < keyList.size(); i++) {
            Integer no = keyList.get(i);
            if (no >= 51) {
                dipaiNo.add(no);
            } else {
                if (i % 3 == 0) {
                    noP1.add(no);
                } else if (i % 3 == 1) {
                    noP2.add(no);
                } else {
                    noP3.add(no);
                }
            }
        }
        Collections.sort(noP1);
        Collections.sort(noP2);
        Collections.sort(noP3);
        Collections.sort(dipaiNo);
        //看牌
        ArrayList<String> player1 = new ArrayList<String>();
        ArrayList<String> player2 = new ArrayList<String>();
        ArrayList<String> player3 = new ArrayList<String>();
        ArrayList<String> dipai = new ArrayList<String>();

        for (Integer integer : noP1) {
            player1.add(pokerMap.get(integer));
        }
        for (Integer integer : noP2) {
            player2.add(pokerMap.get(integer));
        }
        for (Integer integer : noP3) {
            player3.add(pokerMap.get(integer));
        }
        for (Integer integer : dipaiNo) {
            dipai.add(pokerMap.get(integer));
        }

        System.out.println("令狐冲："+player1);
        System.out.println("石破天："+player2);
        System.out.println("鸠摩智："+player3);
        System.out.println("底牌："+dipai);

    }
}
