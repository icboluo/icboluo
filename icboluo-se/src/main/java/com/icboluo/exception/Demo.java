package com.icboluo.exception;

import com.icboluo.util.IcBoLuoException;

import java.io.*;

/**
 * @author icboluo
 * @since 2020-08-10 11:20
 */
class Demo {
    public static void main(String[] args) {
        m1();
    }

    private static void m1() {
//                监听
        try {
            exceptionMethod(1);
//           捕获监听过程出现的异常
//           一个catch块可以捕获多种类型的异常
        } catch (IcBoLuoException | NullPointerException e) {
            System.out.println("程序出现异常");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("程序执行完了");
        }
    }

    public static void m2() {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("a.txt");
        } catch (IOException ioe) {
            // return语句强制方法返回,依然会执行finally
            // 使用exit来退出虚拟机,不会执行finally
            System.exit(1);
        } finally {
            // 关闭磁盘文件，回收资源 .这也是个异常处理嵌套
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
            System.out.println("执行finally块里的资源回收!");
//            释放引用
            fis = null;
        }
    }

    public static void m3() throws IOException {
        try (
                // 声明、初始化两个可关闭的资源
                // try语句会自动关闭这两个资源。
                BufferedReader br = new BufferedReader(
                        new FileReader("a.java"));
                PrintStream ps = new PrintStream(new
                        FileOutputStream("b.txt"))) {
            // 使用两个资源
            System.out.println(br.readLine());
            ps.println("庄生晓梦迷蝴蝶");
        }
    }

    /**
     * throws 用在方法签名中，声明该方法可能抛出异常
     *
     * @param i 随意的参数
     * @throws IcBoLuoException 如果参数不为0则抛出异常
     */
    private static void exceptionMethod(int i) throws IcBoLuoException {
        if (i == 0) {
            System.out.println("程序正常");
        } else {
//            用于抛出异常
            throw new IcBoLuoException();
        }
    }
}
