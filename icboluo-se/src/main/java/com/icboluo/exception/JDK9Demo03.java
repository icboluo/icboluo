package com.icboluo.exception;

import java.io.FileWriter;
import java.io.IOException;


public class JDK9Demo03 {
    public static void main(String[] args) throws IOException {
        FileWriter fw = new FileWriter("a.txt");
        try (fw) {
            fw.write("我是谁");
            fw.flush();
            fw.write("我在哪");
            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
