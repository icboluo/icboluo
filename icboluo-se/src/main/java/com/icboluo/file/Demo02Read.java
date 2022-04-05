package com.icboluo.file;

import com.icboluo.constant.FileRelativePathPre;
import com.icboluo.util.IoHelper;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * <p>字节流会遇到无法解决汉字读取的问题：
 * <p>汉字 : 一个汉字不止一个字节字符集. (GBK 2个字节组成一个汉字, UTF-8 3个字节组成一个汉字)
 * <p>in.read(); 字节的 read 方法就是一个字节一个字节读取数据.
 * <p>英文字符 : a ~ z 每个字符都是一个字节组成的. 读取就是正确的.
 * <p>一个汉字三个字节, 因此如果一个字节一个字节读取, 就会出现数据错误!
 *
 * @author icboluo
 * @since 2020-08-10 14:03
 */
class Demo02Read {
    public static void main(String[] args) throws IOException {
//      创建一个文件输入流对象
        FileInputStream in1 = new FileInputStream(FileRelativePathPre.SE + FileRelativePathPre.RESOURCES + "a.txt");

        StringBuilder sb1 = IoHelper.readFileInputStream(in1);
        // 123ä¸­å½
        System.out.println(sb1);
        in1.close();

//        读到的数据是编码
        FileInputStream in2 = new FileInputStream(FileRelativePathPre.SE + FileRelativePathPre.RESOURCES + "a.txt");
        StringBuilder sb2 = IoHelper.readFileInputStream2(in2);
//        [49, 50, 51, -28, -72, -83, -27, -101][-67, 13, 10, -28, -72, -83, -27, -101]
        System.out.println(sb2);
        in2.close();
    }
}


