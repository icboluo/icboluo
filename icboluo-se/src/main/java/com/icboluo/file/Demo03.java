package com.icboluo.file;

import java.io.FileReader;
import java.io.IOException;

/**
 * @author icboluo
 * @date 2020-08-10 14:16
 */
class Demo03 {
    public static void main(String[] args) throws IOException {
//      创建一个字符读取流
        FileReader fr = new FileReader("a.txt");
        int read;
//      fr.read()：字符流的 read 是根据底层编码表的规则实现读取的.
        while ((read = fr.read()) != -1) {
            System.out.print((char) read);
        }

        //用来保存字符数据的数组
        char[] chars = new char[1024];
        int len;//读取的字符个数

        while ((len = fr.read(chars)) != -1) {
            String str = new String(chars, 0, len);
            System.out.print(str);
        }
        fr.close();
    }
}
