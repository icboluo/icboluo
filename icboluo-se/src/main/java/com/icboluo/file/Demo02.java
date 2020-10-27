package com.icboluo.file;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author icboluo
 */
class Demo02 {
    public static void main(String[] args) throws IOException {
//      创建一个文件输出流对象, 如果文件不存在, 该对象会自动创建该文件，存在则覆盖
        FileOutputStream out = new FileOutputStream("a.txt");
//      向文件中输出数据
        out.write('7');
        out.write('8');
        out.write('9');
//      写入换行 方式一 : \r\n  (了解)，这种方式是win中的写法
        out.write("\r\n".getBytes());
//      输出字节，按照ascii码表输出结果
        out.write(97);
        out.write(98);
        out.write(99);
//      写入换行 方式二 : System.lineSeparator();  获取系统的换行分隔符
        String lineSeparator = System.lineSeparator();
        out.write(lineSeparator.getBytes());

        byte[] bytes = new byte[]{65, 66, 67, 68, 69};
        out.write(bytes);

//      offset 偏移量   length 长度 : 从 b 字节数组中, 哪个 off 偏移位置开始, 取 len 个长度.
//      {65, 66, 67}
        out.write(bytes, 0, 3);
//      {68, 69}
        out.write(bytes, 3, 2);

//      关闭文件输出流对象
        out.close();
        System.out.println("执行完成...");
//      创建了一个文件输出流对象, 实现文件数据的拼接.
        FileOutputStream out2 = new FileOutputStream("a.txt", true);
        out2.write(lineSeparator.getBytes());
//      此时输出'中'，20013超出256范围,发生数据溢出，模运算再按照ascii码表输出
        out2.write('中');
        out2.close();
    }
}