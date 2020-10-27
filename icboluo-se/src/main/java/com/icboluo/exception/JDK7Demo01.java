package com.icboluo.exception;

import java.io.FileWriter;
import java.io.IOException;


/**
 * @author icboluo
 */
public class JDK7Demo01 {
    public static void main(String[] args) {
        FileWriter fw = null;
        try {
            //1.创建对象
            fw = new FileWriter("a.txt");
            fw.write("我是谁");
            //3.刷新操作
            fw.flush();
            fw.write("我在哪");
            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fw != null) {
                try {
                    //4.关闭资源
                    fw.close();//close：让系统资源释放了，但是fw还保存着堆内存中的对象的地址，有可能导致OOM，内存泄漏
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //最后要对这个流对象取消引用
                fw = null;
            }
        }
    }
}
