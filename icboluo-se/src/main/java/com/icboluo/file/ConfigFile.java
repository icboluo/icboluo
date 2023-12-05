package com.icboluo.file;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * <p>配置文件的动态读取
 * <p>1.读取文件所在的地址
 * <p>2.将读取到的数据转换为易操作的数据
 *
 * @author icboluo
 * @since 2020-08-12 16:14
 */
public class ConfigFile {
    public static void main(String[] args) throws IOException {
//        )extends...impl MAP  双列集合
        Properties properties = new Properties();
        //put
        properties.setProperty("001", "一");
        properties.setProperty("002", "二");

        //二
        System.out.println(properties.getProperty("002"));
        //Set<String>  001   002
        System.out.println(properties.stringPropertyNames());
//      这里看不懂了
        Properties properties1 = new Properties();
        FileInputStream fis = new FileInputStream("a.txt");
        properties1.load(fis);
        System.out.println("fis = " + fis);
    }
}
