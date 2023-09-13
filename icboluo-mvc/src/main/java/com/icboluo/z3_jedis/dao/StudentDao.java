package com.icboluo.z3_jedis.dao;

import com.icboluo.z2.JDBCUtil;
import com.icboluo.z3_jedis.bean.Student;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class StudentDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtil.getDataSource());
    public List<Student> queryAll() {
        String sql = "select * from stu_info";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Student.class)
        );
    }
}
