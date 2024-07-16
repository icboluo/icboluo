package com.icboluo.file;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * @author icboluo
 * @since 2024-07-16 下午6:03
 */
public class PropertyRead {
    @Test
    public void classLoader() {
        // 注意：该方法返回的资源文件路径是相当于类加载器的根路径。因此，对于 resources 目录下的文件，需要在文件名前加上 “classpath:" 前缀，例如：”classpath:file.txt“
        // 但是实际测试并不需要
        InputStream is1 = PropertyRead.class.getClassLoader().getResourceAsStream("springmvc-servlet.xml");
        // ❤ 该方法返回的资源文件路径是相当于当前类的路径，因此，对于 resources 目录下的文件，需要在文件名前面加上 “/” 前缀
        InputStream is2 = PropertyRead.class.getResourceAsStream("/springmvc-servlet.xml");

        PropertyResourceBundle prb = isToPrb(is1);
        System.out.println("prb = " + prb);
        Properties toProperties2 = isToProperties(is2);
        System.out.println("toProperties2 = " + toProperties2);
    }

    @Test
    public void file() throws FileNotFoundException {
        //./代表当前目录的路径
        File file1 = new File("./src/main/resources/springmvc-servlet.xml");
        assert file1.exists();

        // 该方法只适用于本地文件系统和 JAR文件。对于 WAR 文件或者其他类型的文件，该方法可能无法正常工作
        File file2 = ResourceUtils.getFile("classpath:springmvc-servlet.xml");
        assert file2.exists();
    }

    @Test
    public void resourceLoader() throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("springmvc-servlet.xml");
        //     <bean id="resourceLoader" class="org.springframework.context.support.ClassPathXmlApplicationContext"></bean>
        ResourceLoader resourceLoader = (ResourceLoader) context.getBean("resourceLoader");
        // ❤
        Resource resource = resourceLoader.getResource("classpath:file.txt");
        InputStream is = resource.getInputStream();

        Properties toProperties = isToProperties(is);
        System.out.println("toProperties = " + toProperties);

        // ❤
        ClassPathResource cpr = new ClassPathResource("springmvc-servlet.xml");
        InputStream is3 = cpr.getInputStream();
        System.out.println(isToProperties(is3));
    }

    // ResourceBundle
    @Test
    public void applicationContext() throws IOException {
        // 这一块在spring编码中稍微麻烦些，不如上面的方法
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("springmvc-servlet.xml");
        Resource resource = applicationContext.getResource("classpath:file.txt");
        InputStream is = resource.getInputStream();

        Properties toProperties = isToProperties(is);
        System.out.println("toProperties = " + toProperties);

        // InputStream is = servletContext.getResourceAsStream("WEB-INF/classes/file.txt");
    }

    @Test
    public void path() throws IOException {
        // ❤ 需要用此方法多校验，就是绝对路径进行校验
        Path path = Paths.get("src/main/resources/springmvc-servlet.xml");
        // 绝对路径，这玩意怎么跑到tomcat底下去了
        System.out.println(path.toAbsolutePath());

        InputStream is = Files.newInputStream(path);
        Properties toProperties = isToProperties(is);
        System.out.println("toProperties = " + toProperties);
    }

    @Test
    public void bundle() {
        // ❤ 专门读取properties
        // 心 ❤
        // 五角星 ☆
        // 黑桃 ♠
        // 梅花 ♥
        // 方块 ♦
        // 圆 ○
        // ◆ ◇
        // □ △ ▽ →
        ResourceBundle bundle = ResourceBundle.getBundle("jedis");
        System.out.println("bundle = " + bundle.getString("host"));
    }

    /**
     * <p>配置文件的动态读取
     * <p>1.读取文件所在的地址
     * <p>2.将读取到的数据转换为易操作的数据
     *
     * @throws IOException Io 异常
     */
    @Test
    public void configFile() throws IOException {
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
        FileInputStream fis = new FileInputStream("./src/main/resources/file.txt");
        properties1.load(fis);
        System.out.println("fis = " + fis);
    }

    private Properties isToProperties(InputStream is) {
        Properties properties = new Properties();
        try {
            properties.load(is);
            return properties;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private PropertyResourceBundle isToPrb(InputStream is) {
        try {
            return new PropertyResourceBundle(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
