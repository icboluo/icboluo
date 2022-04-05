package com.icboluo.file;

import com.icboluo.constant.FileRelativePathPre;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author icboluo
 * @since 2020-08-10 14:25
 */
class Demo06BufferWriter {
    public static void main(String[] args) throws IOException {
        //创建高效的缓冲字符写入流
        BufferedWriter writer = new BufferedWriter(new FileWriter(FileRelativePathPre.SE + FileRelativePathPre.RESOURCES + "a.txt"));
        //写入, 换行, 刷新
        writer.write("I want to use the bathroom ?");
        writer.newLine();
        writer.flush();

        writer.write("go a head.");
        writer.newLine();
        writer.flush();

        writer.close();
    }
}
