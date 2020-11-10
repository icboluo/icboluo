package com.icboluo.file;

import com.icboluo.util.IOHelper;

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
        IOHelper.readFileReader(fr);
        IOHelper.readFileReader2(fr);
        fr.close();
    }
}
