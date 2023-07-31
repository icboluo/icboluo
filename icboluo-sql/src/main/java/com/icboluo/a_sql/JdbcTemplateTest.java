package com.icboluo.a_sql;

import com.icboluo.dataobject.Book;
import com.icboluo.dataobject.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 使用jdbcTemplate框架操作数据库
 * 元数据：列名
 * parametermetadata 参数元数据   封装jdbctemplate的update方法 通过preparestatement的getparametermetadata获取对象
 * resultsetmatadata 结果集元数据 封装jdbctemplate的query方法
 */
@Slf4j
public class JdbcTemplateTest {
    @Test
    public void Test01() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(z_DataSourceSimpleFactory.getDataSource());
        String sql = """
                -- ----------------------------
                -- Table structure for student
                -- ----------------------------
                DROP TABLE IF EXISTS "student_temp";
                CREATE TABLE "student_temp" (
                  "id" integer NOT NULL,
                  "code" integer NOT NULL,
                  "name" text(255) NOT NULL,
                  "age" integer,
                  PRIMARY KEY ("id")
                );
                                """;
        // 此块表并没有创建成功
        jdbcTemplate.execute(sql);
    }

    @Test
    public void Test02() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(z_DataSourceSimpleFactory.getDataSource());
        String sql = "INSERT INTO student (code, name, age) VALUES (?, ?, ?);";
        int i = jdbcTemplate.update(sql, 2003, "李四", 27);
        log.info("insert: " + i);
    }

    @Test
    public void Test03() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(z_DataSourceSimpleFactory.getDataSource());
        String sql = "INSERT INTO student (code, name, age) VALUES (?, ?, ?);";
        Object[] objs = {1007, "jav", 31};
        int i = jdbcTemplate.update(sql, objs);
        log.info("insert: " + i);
    }

    @Test
    public void Test04() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(z_DataSourceSimpleFactory.getDataSource());
        String sql = "update student set code=? where name='jav'";
        Object[] objs = {444};
        int i = jdbcTemplate.update(sql, objs);
        System.out.println(i);
    }

    @Test
    public void Test05() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(z_DataSourceSimpleFactory.getDataSource());
        String sql = "select*from user where username=? and password=?";
        List<User> users = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), "username", "password");
        boolean b = users.size() > 0;
        System.out.println("b = " + b);
    }

    /**
     * 单行单列查询
     */
    @Test
    public void Test11() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(z_DataSourceSimpleFactory.getDataSource());
        String sql = "select count(*) from student";
        Integer result = jdbcTemplate.queryForObject(sql, int.class);
        System.out.println(result);
    }

    @Test//单行多列查询
    public void Test12() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(z_DataSourceSimpleFactory.getDataSource());
        String sql = "select*from book where bid=?";
        Map<String, Object> map = jdbcTemplate.queryForMap(sql, 1);
        System.out.println(map);
    }

    @Test//多行多列查询
    public void Test13() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(z_DataSourceSimpleFactory.getDataSource());
        String sql = "select*from book where bid<?";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql, 100);
        System.out.println(maps);
    }

    @Test
    public void Test21() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(z_DataSourceSimpleFactory.getDataSource());
        String sql = "select*from book where price!=?";
        List<Book> books = jdbcTemplate.query(sql, new RowMapper<Book>() {//行映射成book,ctrl+h查询实现类
            @Override
            public Book mapRow(ResultSet resultSet, int i) throws SQLException {//结果集,行
                Book book = new Book();
                int bid = resultSet.getInt("bid");//bid调用setBid方法
                String bname = resultSet.getString("bname");
                String author = resultSet.getString("author");
                double price = resultSet.getDouble("price");

                book.setAuthor(author);
                book.setBid(bid);
                book.setBname(bname);
                book.setPrice(price);
                return book;
            }
        }, 555);
        for (Book book : books) {
            System.out.println(book);
        }
    }

    @Test
    public void Test22() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(z_DataSourceSimpleFactory.getDataSource());
        String sql = "select*from book where price!=?";
        List<Book> books = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class), 999);
        System.out.println("books = " + books);
    }

}
