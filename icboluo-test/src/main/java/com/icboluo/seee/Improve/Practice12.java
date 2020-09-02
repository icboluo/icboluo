package com.icboluo.seee.Improve;

import java.util.HashSet;
import java.util.Random;

/*
五、双色球规则：双色球每注投注号码由6个红色球号码和1个蓝色球号码组成。红色球号码从1
—33中选择；蓝色球号码从1—16中选择；请随机生成一注双色球号码。（要求同色号码不重复）
 */
public class Practice12 {
    public static void main(String[] args) {
        Random ra = new Random();
        HashSet<Integer> hs = new HashSet<>();
        while (hs.size() < 6) {
            int num = ra.nextInt(33) + 1;
            hs.add(num);
        }
        int blue = ra.nextInt(16) + 1;
        for (Integer red : hs) {
            System.out.print(red + "red");
        }
        System.out.println("\n"+blue + "blue");
    }
}

