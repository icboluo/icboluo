package com.icboluo.seee.Improve;

import java.io.File;
import java.util.Scanner;

/*
描述:从键盘接收一个文件夹路径,获得该文件夹大小并输出到控制台。
答案
操作步骤:
1.	创建键盘录入对象Scanner
2.	定义字符串接收用户输入的文件夹路径
3.	根据文件夹路径创建文件对象
4.	定义一个方法calculate用来计算指定文件夹的大小，接收文件参数，
返回long类型数值表示文件夹的大小。
5.	调用calculate方法传入文件夹对象，在该方法内部获得文件夹中所有文件，得到一个文件数组，
定义一个变量size累加每一个文件的大小，遍历文件数组，判断是否是文件，
如果是文件则获得文件大小并累加到变量size中，如果是文件夹，继续递归调用当前方法。
 */
public class Practice16 {
    public static void main(String[] args) {
        System.out.println("请输入文件夹绝对路径");
        String folderPath = new Scanner(System.in).nextLine();
        File folder = new File(folderPath);
       System.out.println(calculate(folder));
        deleteAll(folder);
    }

    private static long calculate(File dir) {
        long length=0;
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                length += file.length();
            } else {
                length+=calculate(file);
            }
        }
        return length;
    }

    private static void deleteAll(File file) {//强删所有
        if (file.isFile() || file.listFiles().length == 0) {//删除 文件和 文件夹内文件及文件夹为0
            file.delete();
        } else {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {//文件和文件夹个数
                deleteAll(files[i]);
                files[i].delete();
            }
            if (file.exists()) {
                file.delete();
            }
        }
    }
}
