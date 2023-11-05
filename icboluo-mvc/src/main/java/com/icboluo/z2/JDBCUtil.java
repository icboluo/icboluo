package com.icboluo.z2;


import com.alibaba.druid.pool.DruidDataSourceFactory;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.ResourceUtils;

import javax.sql.DataSource;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * @author icboluo
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JDBCUtil {
    //初始化数据源
    private static DataSource dataSource;

    static {
        Properties properties = new Properties();
        try {
            properties.load(JDBCUtil.class.getResourceAsStream("/druid.properties"));
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 获取JdbcTemplate对象
    public static JdbcTemplate getJdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate;
    }

    public static DataSource getDataSource() {
        getResourcesFile();
        return dataSource;
    }

    /**
     * 获取 src/main/resources 下文件的几种方式
     */
    @SneakyThrows
    private static void getResourcesFile() {
        // 注意：该方法返回的资源文件路径是相当于类加载器的根路径。因此，对于 resources 目录下的文件，需要在文件名前加上 “classpath:" 前缀，例如：”classpath:file.txt“
        // 但是实际测试并不需要
        InputStream is1 = JDBCUtil.class.getClassLoader().getResourceAsStream("springmvc-servlet.xml");
        // ❤ 该方法返回的资源文件路径是相当于当前类的路径，因此，对于 resources 目录下的文件，需要在文件名前面奖赏 “/” 前缀
        InputStream is2 = JDBCUtil.class.getResourceAsStream("/springmvc-servlet.xml");
        // ❤
        // Resource resource = resourceLoader.getResource("classpath:file.txt");
        // InputStream is = resource.getInputStream();

        // 该方法只适用于本地文件系统和 JAR文件。对于 WAR 文件或者其他类型的文件，该方法可能无法正常工作
        File file1 = ResourceUtils.getFile("classpath:spring-servlet.xml");

        // Resource resource = applicationContext.getResource("classpath:file.txt");
        // InputStream is = resouce.getInputStream();

        // InputStream is = servletContext.getResourceAsStream("WEB-INF/classes/file.txt");

        //./代表当前目录的路径
        File file2 = new File("./src/main/resources/spring-servlet.xml");

        // ❤ 需要用此方法多校验，就是绝对路径进行校验
        Path path = Paths.get("src/main/resources/spring-servlet.xml");
        // 绝对路径，这玩意怎么跑到tomcat底下去了
        System.out.println(path.toAbsolutePath());
        // InputStream is = Files.newInputStream(path);

        // ❤
        ClassPathResource cpr = new ClassPathResource("springmvc-servlet.xml");
        InputStream is3 = cpr.getInputStream();

        PropertyResourceBundle prb = new PropertyResourceBundle(JDBCUtil.class.getResourceAsStream("/springmvc-servlet"));
        ResourceBundle bundle = ResourceBundle.getBundle("springmvc-servlet");
        System.out.println();
    }
}
