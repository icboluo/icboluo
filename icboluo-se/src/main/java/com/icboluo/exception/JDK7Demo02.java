package com.icboluo.exception;

import java.io.FileWriter;
import java.io.IOException;


/**
 * @author icboluo
 */
public class JDK7Demo02 {
    public static void main(String[] args) {
        try (
                FileWriter fw = new FileWriter("a.txt")
        ) {
            fw.write("我是谁");
            fw.flush();
            fw.write("我在哪");
            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
