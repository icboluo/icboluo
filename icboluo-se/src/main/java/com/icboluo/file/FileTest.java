package com.icboluo.file;

import com.icboluo.constant.FileRelativePathPre;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.net.URL;
import java.util.Properties;

/**
 * 关联硬盘路径, 获取文件对象.
 * 说明 : 文件是需要后缀名的, 如果没有后缀名, 打开文件时, 就会询问使用什么软件来打开该文件.
 * 后缀名的作用是什么 ??? 给软件查看的.  txt (记事本)   jpg, png (图片)  mp4, avi (视频) ...
 * 了解 : 无论硬盘的文件是什么类型, 最终文件中的数据都是 `字节数据`.  (电子原件) 01010101010
 * 软件 : 将对应的 `字节数据` 翻译为对应的 `文件, 图片, 音频, 视频 ...`.
 *
 * @author icboluo
 * @since 2020-08-13 09:59
 */
@Slf4j
public class FileTest {
    /**
     * 通过将给定的路径名字符串转换为抽象路径名来创建新的 File实例
     */
    private final File file = new File(FileRelativePathPre.SE + FileRelativePathPre.RESOURCES + "a.txt");
    /**
     * 从父路径名字符串和子路径名字符串创建新的 File实例
     */
    private final File dirFile = new File(FileRelativePathPre.SE + FileRelativePathPre.RESOURCES, "a.txt");

    private final File parent = new File(FileRelativePathPre.SE + FileRelativePathPre.RESOURCES);
    /**
     * 从父抽象路径名和子路径名字符串创建新的 File实例
     */
    private final File dirFile1 = new File(parent, "a.txt");
    private static int count = 0;


    @Test
    public void test1() throws IOException {
        try {
            // 文件的创建 (只能创建文件, 不能创建文件夹)
            boolean result = file.createNewFile();
            if (result) {
                log.info("如果硬盘上没有该文件，文件可以创建成功");
            } else {
                log.error("如果硬盘上已经存在该文件，文件就会创建失败");
            }
        } catch (IOException e) {
            // 如果指定的抽象路径, 文件的父目录如果不存在, 会发生异常
            log.error("文件父目录不存在,创建失败");
        }

        //文件夹的创建
        // 方式一 : mkdir 创建 `单级` 目录. (如果父目录不存在, 文件夹就会创建失败)
        boolean result1 = parent.mkdir();
        // 方式二 : mkdirs 创建 `单级 / 多级` 目录 . (如果父目录不存在, 会直接创建父目录)
        boolean result2 = parent.mkdirs();

        //获取父目录文件对象
        File parentFile = dirFile.getParentFile();
        //判断父目录是否存在
        if (!parentFile.exists()) {
            System.out.println("父目录不存在, 需要创建父目录.");
            boolean result = parentFile.mkdirs();
        }
        //创建文件对象 (一定要保证父目录存在)
        boolean result3 = dirFile.createNewFile();

        File parentFile1 = dirFile.getParentFile();
        if (parentFile1.exists()) {
            boolean newFile = dirFile.createNewFile();
        } else {
            boolean dirs = parentFile1.mkdirs();
            boolean newFile = dirFile.createNewFile();
        }

/*        情况一 : 文件, 直接删除.
                情况二 : 空文件夹. 也可以直接删除.
                情况三 : 非空文件夹, 不可以删除. (从内向外删除)*/
        boolean result4 = dirFile.delete();
    }

    @Test
    public void test2() throws IOException {
        boolean result1 = dirFile.createNewFile();
        System.out.println("绝对路径->" + dirFile.getAbsolutePath());
        System.out.println("构造路径->" + dirFile.getPath());
        System.out.println("返回由此File表示的文件或目录的名称->" + dirFile.getName());
        System.out.println("文件中的数据长度（字节）->" + dirFile.length());
        System.out.println("父文件夹连续地址，没有盘符地址->" + dirFile.getParentFile());
        //此File表示的文件或目录是否实际存在
        boolean exists = file.exists();
        //此File表示的是否为目录。 是否为目录/文件夹
        boolean directory = file.isDirectory();
        //此File表示的是否为文件
        boolean isFile = file.isFile();

        //获取文件夹对象中的所有 `文件名称清单`
        String[] names = parent.list();
        for (String name : names) {
            //输出文件夹中所有文件的绝对路径
            File f = new File(file, name);
            System.out.print(f.getAbsolutePath() + "***");
        }
        System.out.println();

        //获取 File 文件夹对象中的所有 `文件对象清单`
        File[] files = parent.listFiles();
        for (File f : files) {
            //获取java文件名称
            if (f.getName().endsWith(".java")) {
                System.out.print(f + "***");
            }
        }
        boolean result2 = dirFile.delete();
    }

    @Test
    public void test3() {
        //scanDir(parent);
        //deleteDir(new File("aaa"));
        printDir(parent, ".java");
        deleteNullDir(new File("aaa"));
    }

    @Test
    public void test4() throws IOException {
        File file = new File("src/main/resources/a.txt");
        System.out.println(file.exists());
        System.out.println(new File("src/main/java/com/icboluo/file/FileTest.java").exists());

        FileInputStream fis = new FileInputStream("src/main/resources/application.yml");
        Properties properties = new Properties();
        properties.load(fis);
        System.out.println(properties);
    }

    @Test
    public void test5() {
        URL resource = getClass().getClassLoader().getResource("a.txt");
        // 这个resource.getFile 代表的是完整路径
        System.out.println(new File(resource.getFile()).exists());
    }

    @Autowired
    private ResourceLoader resourceLoader;

    @Test
    public void test6() throws IOException {
        System.out.println(ResourceUtils.getFile("classpath:a.txt").exists());
        Resource resource = resourceLoader.getResource("classpath:a.txt");
        System.out.println(resource.getFile().exists());
    }

    @Autowired
    private ApplicationContext applicationContext;

/*    @Autowired
    private ServletContext servletContext;

    @Test
    public void test7() throws IOException {
        Resource resource = applicationContext.getResource("classpath:a.txt");
        InputStream inputStream1 = resource.getInputStream();
        InputStream inputStream2 = servletContext.getResourceAsStream("WEB-INF/class/a.txt");
    }*/

    private void deleteNullDir(File file) {
        if (file.isFile() && file.delete()) {
            count++;
        }
        File[] files = file.listFiles();
        for (File f : files) {
            if (f.isDirectory()) {
                f.delete();
            }
        }
    }

    private void printDir(File dir, String suffix) {
        //前置做法，直接不要不合法数据
        File[] files = dir.listFiles(pathname ->
                (pathname.isFile() && pathname.getName().endsWith(suffix)) || pathname.isDirectory());
//        实现类做法
        File[] files1 = dir.listFiles(new MyFileFilter(suffix));
//        匿名内部类做法
        File[] files2 = dir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory() || (pathname.isFile() && pathname.getName().endsWith(suffix));
            }
        });
        for (File f : files) {
            //也可以在这里进行过滤都是可以的
            if (f.isFile()) {
                System.out.println("文件名" + f.getAbsolutePath());
            } else {
                printDir(f, suffix);
            }
        }
    }

    private void deleteDir(File dir) {
        File[] files = dir.listFiles();
        for (File f : files) {
            if (f.isDirectory()) {
                deleteDir(f);
            } else {
                boolean delete = f.delete();
            }
        }
        boolean delete = dir.delete();
    }

    private void scanDir(File dir) {
//        获取文件夹中的所有文件对象清单
        File[] files = dir.listFiles();
        for (File f : files) {
            if (f.isDirectory()) {
                System.out.println(f + " -> 文件夹 ...");
                scanDir(f);
            } else {
                System.out.println(f);
            }
        }
    }
}
