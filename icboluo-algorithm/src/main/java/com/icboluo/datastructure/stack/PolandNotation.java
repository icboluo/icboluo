package com.icboluo.datastructure.stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 前缀表达式：波兰表达式求值：从右往左，把数字全部push进栈，然后从左倒右，遇到运算符，pop2个数字运算，然后push，重复直到栈中只剩一个
 * （3+4）*5-6 对应的前缀表达式为：-*+3456
 * 中缀表达式：需要判断遇到操作符的优先级。比较麻烦，所以会出现其他表达式
 * 后缀表达式：逆波兰表达式
 * （3+4）*5-6 对应的后缀表达式求值：34+5*6-：从左向右，遇到数字，push，遇到运算符，先push后一个数，然后pop2个数进行运算，再push，直到栈中只剩一个
 * 逆波兰计算器
 *
 * @author icboluo
 */
class PolandNotation {
    //notation 符号
    public static void main(String[] args) {
        //(3+4)*5-6=>3 4 + 5 * 6 -
        //4*5-8+60+8/2=>4 5 * 8 - 60 + 8 2 / +
        //String suffixExpression = "3 4 + 5 * 6 -";
        String suffixExpression = "4 5 * 8 - 60 + 8 2 / +";
        List<String> list = Arrays.asList(suffixExpression.split(" "));
        int result = calculate(list);
        System.out.println("result = " + result);
        //字符串转换成后缀表达式
        //1+((2+3)*4）-5-》1 2 3 + 4 * + 5 -
        String expression = "1+((2+3)*4)-5";
        List<String> strings = toInfixExpressionList(expression);
        System.out.println("中缀表达式对应的list = " + strings);
        List<String> strings1 = parseSuffixExpressionList(strings);
        System.out.println("后缀表达式对应的list = " + strings1);
    }

    /**
     * 完成中缀表达式到后缀表达式的转换
     */
    public static List<String> parseSuffixExpressionList(List<String> list) {
        Stack<String> s1 = new Stack<>();
        //存储中间结果
        List<String> s2 = new ArrayList<>();
        for (String item : list) {
            //如果是一个数
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if ("(".equals(item)) {
                s1.push(item);
            } else if (")".equals(item)) {
                while (!"(".equals(s1.peek())) {
                    s2.add(s1.pop());
                }
                s1.pop();
            } else {
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)) {
                    s2.add(s1.pop());
                }
                s1.push(item);
            }
        }
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }
        return s2;
    }

    /**
     * 获得中缀表达式的list
     *
     * @param expression 表达式
     */
    public static List<String> toInfixExpressionList(String expression) {
        String s = expression.replaceAll(" ", "");
        List<String> list = new ArrayList<>();
        int i = 0;
        StringBuilder str;
        char c;
        do {
            if ((c = s.charAt(i)) < '0' || (c = s.charAt(i)) > '9') {
                list.add(c + "");
                i++;
            } else {
                str = new StringBuilder();
                while (i < s.length() && (c = s.charAt(i)) > '0' && (c = s.charAt(i)) < '9') {
                    str.append(c);
                    i++;
                }
                list.add(str.toString());
            }
        } while (i < s.length());
        return list;
    }

    public static int calculate(List<String> list) {
        Stack<String> stack = new Stack<>();

        for (String s : list) {
            //匹配的是多位数
            if (s.matches("\\d+")) {
                stack.push(s);
            } else {
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
/*                int result = switch (s) {
                    case "+" -> num1 + num2;
                    case "-" -> num1 - num2;
                    case "*" -> num1 * num2;
                    case "/" -> num1 / num2;
                    default -> throw new RuntimeException("运算符有误");
                };*/
                int result = 10;
                stack.push("" + result);
            }
        }
        return Integer.parseInt(stack.pop());
    }

    public void end() {

    }
}
