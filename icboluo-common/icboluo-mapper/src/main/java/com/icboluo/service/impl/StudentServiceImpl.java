package com.icboluo.service.impl;

import com.icboluo.entity.Student;
import com.icboluo.service.StudentService;
import com.icboluo.util.RandomHelper;
import com.zaxxer.hikari.HikariDataSource;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author icboluo
 * @since 2023-07-31 22:36
 */
@Slf4j
public class StudentServiceImpl implements StudentService {

    Connection conn;

    {
        try (HikariDataSource dataSource = new HikariDataSource()) {
            dataSource.setJdbcUrl("jdbc:sqlite::../../document/sql/document.db");
            dataSource.setDriverClassName("org.sqlite.JDBC");
            conn = dataSource.getConnection();
        } catch (SQLException ex) {
            log.error("init", ex);
        }
    }

    @Override
    public List<Student> generateList(int size) {
        List<Student> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            String name = generateZhName();
            Student student = new Student();
            student.setId(i);
            student.setCode(Integer.valueOf("7" + String.format("%02d", i)));
            student.setName(name);
            student.setAge(RandomHelper.nextInt(100));
            list.add(student);
        }
        return list;
    }

    @SneakyThrows
    @Override
    public String generateEnName() {
        Statement st = conn.createStatement();
        ResultSet result1 = st.executeQuery("select count(*) from name where surname_or_name='surname' and language ='en'");
        result1.next();
        int enSurnameCount = result1.getInt("count(*)");

        ResultSet result2 = st.executeQuery("select count(*) from name where surname_or_name='name' and language ='en'");
        result2.next();
        int enNameCount = result2.getInt("count(*)");

        int a = RandomHelper.nextInt(enSurnameCount);
        ResultSet result3 = st.executeQuery("select name from name where surname_or_name='surname' and language ='en' limit " + a + "," + (a + 1));
        result3.next();
        String enSurName = result3.getString("name");

        int b = RandomHelper.nextInt(enNameCount);
        ResultSet result4 = st.executeQuery("select name from name where surname_or_name='name' and language ='en' limit " + b + "," + (b + 1));
        result4.next();
        String enName = result4.getString("name");

        return enSurName + " " + enName;
    }

    @SneakyThrows
    @Override
    public String generateZhName() {
        Statement st = conn.createStatement();
        ResultSet result1 = st.executeQuery("select count(*) from name where surname_or_name='surname' and language ='zh'");
        result1.next();
        int zhSurnameCount = result1.getInt("count(*)");

        ResultSet result2 = st.executeQuery("select count(*) from name where surname_or_name='name' and language ='zh'");
        result2.next();
        int zhNameCount = result2.getInt("count(*)");

        int a = RandomHelper.nextInt(zhSurnameCount);
        ResultSet result3 = st.executeQuery("select name from name where surname_or_name='surname' and language ='zh' limit " + a + "," + (a + 1));
        result3.next();
        String zhSurName = result3.getString("name");

        int b = RandomHelper.nextInt(zhNameCount);
        ResultSet result4 = st.executeQuery("select name from name where surname_or_name='name' and language ='zh' limit " + b + "," + (b + 1));
        result4.next();
        String zhName = result4.getString("name");

        return zhSurName + zhName;
    }
}
