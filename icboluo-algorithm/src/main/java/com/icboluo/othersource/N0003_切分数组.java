package com.icboluo.othersource;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.function.BiFunction;

public class N0003_切分数组 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String str = sc.nextLine();
            String[] arr = toArray(str);
            String res = fun(arr);
            System.out.println(res);
        }
    }

    /**
     * 切分数组
     *
     * @param str
     * @return
     */
    private static String[] toArray(String str) {
        char[] chars = str.toCharArray();
        List<String> list = new ArrayList<>();
        String temp = "";
        for (int i = 0; i < chars.length; i++) {
            char aChar = chars[i];
            if (aChar == '(' || aChar == ')') {
//                收集括号前的结果
                if (!temp.equals("")) {
                    list.add(temp);
                    temp = "";
                }
                list.add(String.valueOf(aChar));
            }
//            这两个判断一样，暂时不优化
            if (aChar >= 'a' && aChar <= 'z') {
                temp += aChar;
            }
            if (aChar >= '0' && aChar <= '9') {
                temp += aChar;
            }
            if (aChar == '-') {
                temp += aChar;
            }
//            收集空格前的结果/ 注意，多个空格的情况在这里消灭
            if (aChar == ' ' && !"".equals(temp)) {
                list.add(temp);
                temp = "";
            }
        }
        return list.toArray(new String[0]);
    }

    /**
     * 括号划分
     * (div 12 (sub 45 45))
     *
     * @param arr 被切分好的数组
     * @return 计算结果
     */
    private static String fun(String[] arr) {
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            String item = arr[i];
            if (!item.equals(")")) {
                stack.push(item);
            } else {
                int b = Integer.parseInt(stack.pop());
                int a = Integer.parseInt(stack.pop());
                String operation = stack.pop();
                String zuoKuoHao = stack.pop();
                Optional<Integer> tempOpt = operation(a, b, operation);
                if (!tempOpt.isPresent()) {
                    return "error";
                }
                stack.push(tempOpt.get() + "");
            }
        }
        return stack.pop();
    }

    /**
     * @param a
     * @param b
     * @param operation
     * @return
     */
    private static Optional<Integer> operation(int a, int b, String operation) {
        if (operation.equals("div") && b == 0) {
            return Optional.empty();
        }
        Map<String, BiFunction<Integer, Integer, Integer>> map = new HashMap<>();
        map.put("add", Integer::sum);
        map.put("sub", (fir, sec) -> fir - sec);
        map.put("mul", (fir, sec) -> fir * sec);
        map.put("div", (fir, sec) -> {
            BigDecimal aBd = BigDecimal.valueOf(fir);
            BigDecimal bBd = BigDecimal.valueOf(sec);
            BigDecimal bd = aBd.divide(bBd, RoundingMode.FLOOR).setScale(0, RoundingMode.FLOOR);
            return bd.intValue();
        });
        BiFunction<Integer, Integer, Integer> bif = map.get(operation);
        Integer ans = bif.apply(a, b);
        return Optional.of(ans);
    }
}
