package com.icboluo.seee.Improve;

import java.util.Scanner;

public class NextAndNextLine {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
/*
next()从遇到第一个有效字符（非空格、换行符）开始扫描，遇到第一个分隔符或结束符
（空格’ ‘或者换行符 ‘\n’）时结束。 nextLine()则是扫描剩下的所有字符串知道遇到回车为止。

在计算机中实际表示是包含三个空格和一个换行符：
aaa bbb ccc \n
nextLine()函数从空格开始扫描知直到遇到\n符结束，故得到字符串是 ” bbb ccc”，注意这里bbb前面还有一个空格
联合使用时就是一个整体，next在前，nextLine靠后，
扫描接受的是同一行上的字符串，只不过接收的是不同内容罢了。
        //加入输入的是:aaa bbb ccc
        String str1 = sc.next();
        //str1="aaa"
        String str2 = sc.nextLine();
        //str2=" bbb ccc"
        */
/*
nextInt() 输入10，录入10\r\n,第二个nextLine读取到\r\n就结束了
next（） 字符串中包含空格，只会获取第一个作为接收的字符串
nextLine（） 以回车符作为接收结束的标志
 */
        System.out.println("------------------------");
        System.out.println(sc.nextLine());
        System.out.println(sc.nextInt());
        System.out.println(sc.nextLine());
        System.out.println("------------------------");


/*下面对于sc.nextInt() ，sc.nextDouble等与sc.nextLine()使用时需要注意的问题
42
3.1415
Welcome to HackerRank Java tutorials!
用户的输入在计算机缓冲区中是这样存储的：42\n3.1415\nWelcome to HackerRank Java tutorials!\n
使用nextInt()读取42结束，剩下\n3.1415\nWelcome to HackerRank Java
使用nextDouble()读取3.1415，剩下\nWelcome to HackerRank Java
如果此时直接用nextLine()读取，该函数遇到换行符\n就结束
所以需要在这之前输入nextLine()，去掉缓冲区的\n，然后再读取
这是由于nextLine()会读取\n之前的所有内容，并去除\n，而nextDouble()等曾不会去除它后面的\n
*/
        /*int x = sc.nextInt();
        double y = sc.nextDouble();
        sc.nextLine();  //to flush the character \n left by method nextDouble()
        String s = sc.nextLine();
        System.out.println("String: " + s);
        System.out.println("Double: " + y);
        System.out.println("Int: " + x);*/
    }
}

