package com.icboluo.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.*;

/**
 * @author icboluo
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FileHelper {

    /**
     * 静默删除
     * 递归删除
     *
     * @param file 要删除的文件
     */
    public static void delete(File file) {
/*        import org.apache.commons.io.FileUtils;
        FileUtils.deleteQuietly(file);*/
    }

    public static void write(String data, String filePath) {
        try (FileWriter fw = new FileWriter(filePath)) {
            fw.write(data);
            fw.flush();
            /*            fw.close();*/
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String read(String filePath) {
        try (FileReader fr = new FileReader(filePath)) {
            char[] chars = new char[1024];
            int len;
            String s = null;
            while ((len = fr.read(chars)) != -1) {
                s = new String(chars, 0, len);
            }
            /*fr.close();*/
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
