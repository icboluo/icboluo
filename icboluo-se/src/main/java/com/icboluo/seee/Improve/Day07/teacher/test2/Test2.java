package com.icboluo.seee.Improve.Day07.teacher.test2;

import java.util.Random;

public class Test2 {
    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            int num = new Random().nextInt(1000);
            System.out.println(num);
        }

        System.out.println("------------------");

        for (int i = 0; i < 10; i++) {
            long num = (long) (Math.random() * 1000);
            System.out.println(num);
        }

    }
}
