package com.icboluo.file;

import com.icboluo.common.AbstractFilePathConstant;

import java.io.*;

/**
 * @author icboluo
 * @since 2020-08-12 14:26
 */
public class Demo07 {
    public static void main(String[] args) throws IOException {
//        利用高效字节输出流往C盘下的d.txt文件输出一个字节数
        FileOutputStream fos = new FileOutputStream("a.txt");
        BufferedOutputStream bos1 = new BufferedOutputStream(fos);
        bos1.write(97);
        bos1.close();

//        利用高效字节输出流往C盘下的e.txt文件写出一个字节数组数据
        BufferedOutputStream bos2 = new BufferedOutputStream(new FileOutputStream("a.txt"));
        byte[] b = "abcd".getBytes();
        bos2.write(b);
        bos2.close();

//        利用高效字节输入流和高效字节输出流完成文件的复制
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(AbstractFilePathConstant.A));
        BufferedOutputStream bos3 = new BufferedOutputStream(new FileOutputStream(AbstractFilePathConstant.B));

        int len;
//        buffer本身就加速了，然后一次读1024又加速一次
        byte[] bytes = new byte[1024];
        while ((len = bis.read(bytes)) != -1) {
            bos3.write(bytes, 0, len);
        }
        bos3.close();
        bis.close();
    }
}
