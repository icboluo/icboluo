package com.icboluo.file;

import com.icboluo.constant.FileRelativePathPre;
import com.icboluo.util.IoUtil;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author icboluo
 * @since 2020-08-10 14:19
 */
class a2_FileWriter {
    @Test
    public void test1() throws IOException {
//      创建一个字符文件写入流对象
        FileWriter fw = new FileWriter(FileRelativePathPre.SE + FileRelativePathPre.RESOURCES + "a.txt");
//      写入数据  数据不是直接输出到硬盘的文件，它会先将数据保存到程序的缓冲区,必须通过刷新操作才会写到文件中
//      字符流写入字符数据, 无需实现字节转换, 因为字符流底层拥有默认编码表.
        fw.write("How are you doing ?");
//      刷新操作
        fw.flush();
        fw.write("你好吗?");
//      close方法底层，会直接调用一次flush
/*        close：表示流已经被释放资源，不能再操作文件。如果还要使用只能重新创建对象
        flush:将缓冲区的内容输出到文件，但是文件资源没有释放的，还能继续操作

        注意事项：在一次流操作中，在流关闭之前所有的操作，都是续写*/
        String str = "我爱java，java爱我";
        fw.write(str, 7, 6);
        fw.close();
    }

    @Test
    public void test2() throws IOException {
        // 创建一个字符读取流，流只能读取一次
        FileReader fr = new FileReader(FileRelativePathPre.SE + FileRelativePathPre.RESOURCES + "a.txt");
        StringBuilder sb1 = IoUtil.readFileReader1(fr);
        System.out.println(sb1);
        System.out.println("-------------------------------");
        StringBuilder sb2 = IoUtil.readFileReader2(fr);
        System.out.println(sb2);
        fr.close();
    }
}
