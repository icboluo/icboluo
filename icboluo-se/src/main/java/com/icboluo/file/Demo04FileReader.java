package com.icboluo.file;

import com.icboluo.constant.FileRelativePathPre;
import com.icboluo.util.IoHelper;

import java.io.FileReader;
import java.io.IOException;

/**
 * @author icboluo
 * @since 2020-08-10 14:16
 */
class Demo04FileReader {
    public static void main(String[] args) throws IOException {
        // 创建一个字符读取流，流只能读取一次
        FileReader fr = new FileReader(FileRelativePathPre.SE + FileRelativePathPre.RESOURCES + "a.txt");
        StringBuilder sb1 = IoHelper.readFileReader1(fr);
        System.out.println(sb1);
        System.out.println("-------------------------------");
        StringBuilder sb2 = IoHelper.readFileReader2(fr);
        System.out.println(sb2);
        fr.close();
    }
}
