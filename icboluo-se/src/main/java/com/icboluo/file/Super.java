package com.icboluo.file;

import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * @author icboluo
 * @since 2022-04-28 22:38
 */
public class Super {
    /**
     * 字符串转换为文件
     */
    @Test
    public void strToFile() {
        // 不同文件类型本质上是一致的，用字符串转换成file，再改一下后缀即可
        String str = "abc";
        // 奇怪，为什么这里的路径是本项目地下，而其他的缺在总项目底下
        File file = new File("./src/main/resources/a.txt");
        BufferedReader br = new BufferedReader(new StringReader(str));
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));) {
            char[] cha = new char[1024];
            int len;
            while ((len = br.read(cha)) != -1) {
                bw.write(cha, 0, len);
            }
            bw.flush();
            br.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
