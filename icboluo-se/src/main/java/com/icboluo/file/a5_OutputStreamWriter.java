package com.icboluo.file;

import com.icboluo.constant.FileRelativePathPre;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author icboluo
 * @since 2020-08-12 15:03
 */
class a5_OutputStreamWriter {
    public static void main(String[] args) throws IOException {
        String str = "我爱Java";
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(FileRelativePathPre.SE + FileRelativePathPre.RESOURCES + "a.txt"), StandardCharsets.UTF_8);
        osw.write(str);
        osw.close();

        InputStreamReader isr = new InputStreamReader(new FileInputStream(FileRelativePathPre.SE + FileRelativePathPre.RESOURCES + "a.txt"), StandardCharsets.UTF_8);
        int len;
        char[] chars = new char[1024];
        while ((len = isr.read(chars)) != -1) {
            System.out.println(new String(chars, 0, len));
        }
        isr.close();
    }
}
