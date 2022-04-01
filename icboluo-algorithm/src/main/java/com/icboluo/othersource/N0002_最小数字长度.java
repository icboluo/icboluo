package com.icboluo.othersource;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Scanner;

public class N0002_最小数字长度 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String str = sc.nextLine();
            String[] arr = str.split(" ");
            BigDecimal total = BigDecimal.valueOf(Long.parseLong(arr[0]));
            int aSum = Integer.parseInt(arr[1]);
            int res = minLength(total, aSum);
            System.out.println(res);
        }
    }

    /**
     * 最小数字长度
     *
     * @param total 总人数
     * @param aSum  字母个数
     * @return 最小数字个数
     */
    private static int minLength(BigDecimal total, int aSum) {
        BigInteger aTotal = BigInteger.valueOf(26).pow(aSum);
//        总数除以字母就数数字需要的 ，下一行/下下一行代码好像没有必要，不需要格式化bd，考试暂时不处理
        BigDecimal bdTotal = total.setScale(0, RoundingMode.UP);
        BigDecimal bdATotal = BigDecimal.valueOf(aTotal.intValue()).setScale(0, RoundingMode.UP);
        BigDecimal bdNum = bdTotal.divide(bdATotal, RoundingMode.UP).setScale(0, RoundingMode.UP);

//        等于1， 已经说明数字个数不能为0
        int weishu = 1;
//        等于10说明刚好除经，不用增加数字位数
        while (bdNum.compareTo(BigDecimal.TEN) > 0) {
            bdNum = bdNum.divide(BigDecimal.TEN, RoundingMode.UP);
            weishu++;
        }
        return weishu;
    }
}
