package com.icboluo.se.file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * 文件字符缓冲流
 *
 * @author icboluo
 * @date 2020-08-10 14:23
 */
class Demo05 {
    public static void main(String[] args) throws IOException {
//       创建高效的缓冲字符读取流
        BufferedReader reader = new BufferedReader(new FileReader("a.txt"));
//        一行一行读，不包括换行符
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        reader.close();
    }
}
