package com.icboluo.util;

import java.io.*;

/**
 * @author icboluo
 */
public class FileHelper {
    public static void write(String data, String filePath) {
        FileWriter fw;
        try {
            fw = new FileWriter(filePath);
            fw.write(data);
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String read(String filePath) {
        try {
            FileReader fr = new FileReader(filePath);
            char[] chars = new char[1024];
            int len;
            String s = null;
            while ((len = fr.read(chars)) != -1) {
                s = new String(chars, 0, len);
            }
            fr.close();
            return s;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            boolean delete = file.delete();
        }
    }

    public static InputStream getInputStream(File file) {
        InputStream is = null;
        try {
            is = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return is;
    }
}
