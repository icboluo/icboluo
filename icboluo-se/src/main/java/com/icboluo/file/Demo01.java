package com.icboluo.file;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * <p>字节流会遇到无法解决汉字读取的问题：
 * <p>汉字 : 一个汉字不止一个字节字符集. (GBK 2个字节组成一个汉字, UTF-8 3个字节组成一个汉字)
 * <p>in.read(); 字节的 read 方法就是一个字节一个字节读取数据.
 * <p>英文字符 : a ~ z 每个字符都是一个字节组成的. 读取就是正确的.
 * <p>一个汉字三个字节, 因此如果一个字节一个字节读取, 就会出现数据错误!
 *
 * @author icboluo
 * @date 2020-08-10 14:03
 */
 class Demo01 {
    public static void main(String[] args) throws IOException {
//      创建一个文件输入流对象
        FileInputStream in = new FileInputStream("a.txt");

//      读取数据
        int read;
//      in.read()：一个字节一个字节实现数据的读取
        while ((read = in.read()) != -1) {
//          输出的数据内容是根据类型来判断.
            System.out.println((char) read);
        }
        in.close();

        FileInputStream in2 = new FileInputStream("a.txt");
        byte[] buf = new byte[8];
        int len;
//      in.read(buf); 一个字节数组一个字节数组实现数据读取.
        while ((len = in2.read(buf)) != -1) {
            System.out.println(Arrays.toString(buf));
            System.out.println(len);
        }
        in2.close();
    }
}


