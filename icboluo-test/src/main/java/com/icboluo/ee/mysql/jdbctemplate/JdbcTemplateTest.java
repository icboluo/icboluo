package com.icboluo.ee.mysql.jdbctemplate;

import com.icboluo.ee.mysql.Book;
import com.icboluo.ee.mysql.MyDataSourceFactory;
import org.junit.Test;
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
public class JdbcTemplateTest {
    @Test
    public void Test01() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(MyDataSourceFactory.getDataSource(2));
        String sql = "create table book(bid int primary key auto_increment,bname varchar(20),price double,author varchar(20));";
        jdbcTemplate.execute(sql);
    }

    @Test
    public void Test02() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(MyDataSourceFactory.getDataSource(2));
        String sql = "insert into book values(?,?,?,?)";
        int i = jdbcTemplate.update(sql, null, "java", 666, "laowang");
        System.out.println(i);
    }

    @Test
    public void Test03() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(MyDataSourceFactory.getDataSource(2));
        String sql = "insert into book values(?,?,?,?)";
        Object[] objs = {null, "jav", 66, "lao"};
        int i = jdbcTemplate.update(sql, objs);
        System.out.println(i);
    }

    @Test
    public void Test04() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(MyDataSourceFactory.getDataSource(2));
        String sql = "update book set price=? where bname='jav'";
        Object[] objs = {444};
        int i = jdbcTemplate.update(sql, objs);
        System.out.println(i);
    }

    /**
     * 单行单列查询
     */
    @Test
    public void Test11() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(MyDataSourceFactory.getDataSource(2));
        String sql = "select count(*) from user";
        Integer result = jdbcTemplate.queryForObject(sql, int.class);
        System.out.println(result);
    }

    @Test//单行多列查询
    public void Test12() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(MyDataSourceFactory.getDataSource(2));
        String sql = "select*from book where bid=?";
        Map<String, Object> map = jdbcTemplate.queryForMap(sql, 1);
        System.out.println(map);
    }

    @Test//多行多列查询
    public void Test13() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(MyDataSourceFactory.getDataSource(2));
        String sql = "select*from book where bid<?";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql, 100);
        System.out.println(maps);
    }

    @Test
    public void Test21() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(MyDataSourceFactory.getDataSource(2));
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
        JdbcTemplate jdbcTemplate = new JdbcTemplate(MyDataSourceFactory.getDataSource(2));
        String sql = "select*from book where price!=?";
        List<Book> books = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class), 999);
        System.out.println("books = " + books);
    }

}
