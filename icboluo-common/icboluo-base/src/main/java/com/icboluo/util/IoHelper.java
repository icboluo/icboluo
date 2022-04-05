package com.icboluo.util;

import lombok.SneakyThrows;

import java.io.*;
import java.util.Arrays;

/**
 * @author icboluo
 * @since 2020/11/10 22:15
 */
public class IoHelper {

    /**
     * Serialize the given object to a byte array.
     *
     * @param object the object to serialize
     * @return an array of bytes representing the object in a portable fashion
     */
    public static byte[] serialize(Object object) {
        if (object == null) {
            return null;
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
        try {
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            oos.flush();
        } catch (IOException ex) {
            throw new IllegalArgumentException("Failed to serialize object of type: " + object.getClass(), ex);
        }
        return baos.toByteArray();
    }

    /**
     * inputStream转outputStream
     *
     * @param in
     * @return
     * @throws Exception
     */
    public ByteArrayOutputStream parse(final InputStream in) throws Exception {
        final ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        int ch;
        while ((ch = in.read()) != -1) {
            swapStream.write(ch);
        }
        return swapStream;
    }

    /**
     * outputStream转inputStream
     *
     * @param out
     * @return
     */
    public ByteArrayInputStream parse(final OutputStream out) {
        ByteArrayOutputStream baos = (ByteArrayOutputStream) out;
        final ByteArrayInputStream swapStream = new ByteArrayInputStream(baos.toByteArray());
        return swapStream;
    }

    /**
     * @param bufferedReader 文件字符缓冲流
     * @return read result
     * @throws IOException
     */
    public static String readBufferedReader(BufferedReader bufferedReader) throws IOException {
        //       创建高效的缓冲字符读取流
//        BufferedReader reader = new BufferedReader(new FileReader("a.txt"));

        StringBuilder sb = new StringBuilder();
        String line;
//      一行一行读，不包括换行符
        while ((line = bufferedReader.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }

    @SneakyThrows(IOException.class)
    public static String readBufferedReader(InputStreamReader inputStreamReader) {
        StringBuilder sb = new StringBuilder();
        String line;
        BufferedReader br = new BufferedReader(inputStreamReader);
        try (br) {
            while ((line = br.readLine()) != null) {
//                        拼接换行
                sb.append(line).append(System.lineSeparator());
            }
        }
        return sb.toString();
    }

    /**
     * * <p>字节流会遇到无法解决汉字读取的问题：
     * * <p>汉字 : 一个汉字不止一个字节字符集. (GBK 2个字节组成一个汉字, UTF-8 3个字节组成一个汉字)
     * * <p>in.read(); 字节的 read 方法就是一个字节一个字节读取数据.
     * * <p>英文字符 : a ~ z 每个字符都是一个字节组成的. 读取就是正确的.
     * * <p>一个汉字三个字节, 因此如果一个字节一个字节读取, 就会出现数据错误!
     *
     * @param fis fis
     * @return sb
     * @throws IOException 读取异常
     */
    public static StringBuilder readFileInputStream(FileInputStream fis) throws IOException {
        StringBuilder sb = new StringBuilder();
//      读取数据
        int read;
//      fis.read()：一个字节一个字节实现数据的读取
        while ((read = fis.read()) != -1) {
//          输出的数据内容是根据类型来判断.
            sb.append((char) read);
        }
        return sb;
    }

    public static StringBuilder readFileInputStream2(FileInputStream fis) throws IOException {
        StringBuilder sb = new StringBuilder();
        byte[] buf = new byte[8];
        int len;
//      in.read(buf); 一个字节数组一个字节数组实现数据读取.
        while ((len = fis.read(buf)) != -1) {
            sb.append(Arrays.toString(buf));
            System.out.println("读到的数据长度=" + len);
        }
        return sb;
    }

    public static StringBuilder readFileReader1(FileReader fr) throws IOException {
        StringBuilder sb = new StringBuilder();
        int read;
//      fr.read()：字符流的 read 是根据底层编码表的规则实现读取的.
        while ((read = fr.read()) != -1) {
            sb.append((char) read);
        }
        return sb;
    }

    public static StringBuilder readFileReader2(FileReader fr) throws IOException {
        StringBuilder sb = new StringBuilder();
        //用来保存字符数据的数组
        char[] chars = new char[1024];
        int len;//读取的字符个数
        while ((len = fr.read(chars)) != -1) {
            String str = new String(chars, 0, len);
            sb.append(str);
        }
        return sb;
    }
}
