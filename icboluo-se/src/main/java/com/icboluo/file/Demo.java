package com.icboluo.file;

import com.icboluo.common.AbstractFilePathConstant;

import java.io.*;

/**
 * 根据流向可以分为（都是以内存为中心）：输入流（硬盘到内存中），输出流（内存到硬盘中）
 * 根据数据类型可以分为：字节流，字符流
 * 整个file用后缀区分输入流和输出流，奇数后缀代表输入流，偶数后缀代表输出流
 * InputStream(字节输入流的顶层抽象父类)，它是一个顶层抽象父类，由于是抽象类，所以要用子类：FileInputStream
 * OutputStream(字节输出流的顶层抽象父类)，但是它是抽象的只能使用它的常用子类：FileOutputStream
 * 注意：字节流都是在操作字节的，byte，一个字节只有256个数据
 * <p>
 * Reader(字符输入流的顶层抽象父类)，我们要使用它的常用子类FileReader
 * Writer(字符输出流的顶层抽象父类)，我们要使用它
 * <p>
 * BufferedInputStream 高效字节输入流
 * BufferedOutputStream 高效字节输出流
 *
 * ObjectInputStream
 * OutputStreamWriter
 * PrintStream
 *
 * @author icboluo
 * @since 2020-08-10 14:26
 */
class Demo {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream("a.txt");
        FileInputStream fis = new FileInputStream("a.txt");
        FileReader fr = new FileReader("a.txt");
        FileWriter fw = new FileWriter("a.txt");
        BufferedWriter bw = new BufferedWriter(fw);
        BufferedReader br = new BufferedReader(fr);
        new ObjectInputStream(new FileInputStream(AbstractFilePathConstant.A));
        new OutputStreamWriter(new FileOutputStream(AbstractFilePathConstant.A));
        new PrintStream(new FileOutputStream(AbstractFilePathConstant.A));
        try (fos; fis; fr; fw; bw; br) {
            fos.write('a');
            int len;
            byte[] bytes = new byte[8];
            while ((len = fis.read(bytes)) != -1) {
                System.out.println(len);
                System.out.println(new String(bytes, 0, len));
            }
            fw.write('b');
            fw.flush();
            int read;
            while ((read = fr.read()) != -1) {
                System.out.println((char) read);
            }
            bw.write("c");
            bw.flush();
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
//            2个copy
            while ((read = fis.read()) != -1) {
                fos.write(read);
            }
            while ((len = fis.read(bytes)) != -1) {
                fos.write(bytes, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
