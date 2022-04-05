package com.icboluo.file;

import com.icboluo.constant.FileRelativePathPre;

import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 * @author icboluo
 * @since 2020-08-12 15:16
 */
class Demo13Print {
    public static void main(String[] args) throws FileNotFoundException {
        String str = "print stream";
        PrintStream ps = new PrintStream(FileRelativePathPre.SE + FileRelativePathPre.RESOURCES + "a.txt");
        ps.println(str);
        ps.close();
        // 修改打印位置，看不懂
        System.setOut(ps);
    }
}
