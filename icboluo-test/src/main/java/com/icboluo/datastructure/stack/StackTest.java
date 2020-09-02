package com.icboluo.datastructure.stack;


import org.junit.jupiter.api.Test;

import java.util.Scanner;

/**
 * 计算器：3+2*6-1
 * 3入数栈，
 * +入运算符栈，2入数栈
 * *高于+优先级，入运算符栈，6入数栈
 * -低于*优先级，先pop出数栈的2个数据和运算符栈的一个运算符，运算出来重新入数栈，当前的运算符重新入运算符栈
 * 最后2个数一个运算符pop出来运算当做一个数，持续直到数栈只剩一个元素
 *
 * @author lp
 */
public class StackTest {

    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(4);
        String k;
        //是否退出菜单
        boolean b = true;
        Scanner sc = new Scanner(System.in);
        while (b) {
            System.out.println("show：显示栈");
            System.out.println("push：添加数据到栈");
            System.out.println("pop：从栈取出数据");
            System.out.println("exit：退出程序");
            k = sc.next();
            switch (k) {
                case "show":
                    stack.list();
                    break;
                case "push":
                    System.out.println("请输入一个数：");
                    int value = sc.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        int pop = stack.pop();
                        System.out.println("出栈的数据为：" + pop);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    sc.close();
                    b = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出");
    }

    /**
     * 计算器测试（中缀表达式）
     */
    @Test
    public void test1() {
        //表达
        String expression = "3+2*6-1";
        ArrayStack numStack = new ArrayStack(10);
        ArrayStack operStack = new ArrayStack(20);
        int index = 0;
        int num1;
        int num2;
        int operation;
        int result;
        char ch;
        StringBuilder keepNum = new StringBuilder();
        while (index < expression.length()) {
            ch = expression.substring(index, index + 1).charAt(0);
            if (operStack.isOperation(ch)) {
                if (!operStack.isEmpty()) {
                    //如果当前优先级高,需要处理
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        operation = operStack.pop();
                        result = numStack.calculate(num1, num2, operation);
                        numStack.push(result);
                        operStack.push(ch);
                    } else {
                        operStack.push(ch);
                    }
                } else {
                    operStack.push(ch);
                }
            } else {
                //处理多位数
                keepNum.append(ch);
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum.toString()));
                } else {
                    if (operStack.isOperation(expression.substring(index + 1, index + 2).charAt(0))) {
                        numStack.push(Integer.parseInt(keepNum.toString()));
                        keepNum = new StringBuilder();
                    }
                }
            }
            index++;
        }
        while (!operStack.isEmpty()) {
            num1 = numStack.pop();
            num2 = numStack.pop();
            operation = operStack.pop();
            result = numStack.calculate(num1, num2, operation);
            numStack.push(result);
        }
        System.out.printf("表达式：%s=%d ", expression, numStack.pop());
    }

}
