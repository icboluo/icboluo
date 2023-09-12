package com.icboluo.z2.dao;


import com.icboluo.z2.bean.User;
import com.icboluo.z2.JDBCUtil;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserDaoImpl implements UserDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtil.getDataSource());

    @Override
    public List<User> queryAll() {
        String sql = "select*from t_user";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public int deleteById(String id) {
        String sql = "delete from t_user where id=?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public User queryById(String id) {
        String sql = "select*from t_user where id =?";
        try {
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), id);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int updateUser(User user) {
        String sql = "update t_user set name=?,sex=?,age=?,address=?,qq=?,email=?where id=?";
        int update = jdbcTemplate.update(sql,
                user.getName(),
                user.getSex(),
                user.getAge(),
                user.getAddress(),
                user.getQq(),
                user.getEmail(),
                user.getId()
        );
        return update;
    }

    @Override
    public int saveUser(User user) {
        String sql = "insert into t_user values(null,?,?,?,?,?,?)";
        int update = jdbcTemplate.update(sql,
                user.getName(),
                user.getSex(),
                user.getAge(),
                user.getAddress(),
                user.getQq(),
                user.getEmail()
        );
        return update;
    }

    @Override
    public List<User> queryByUserName(String userName) {
        String sql = "select*from t_user where name=?";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(User.class),userName);
    }

    @Override
    public List<User> pageQuery(int startCount, int pageSize) {
        String sql = "select*from t_user limit ?,?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), startCount, pageSize);
    }

    @Override
    public int queryTotalCount() {
        String sql = "select count(*)from t_user";
        try {
         return jdbcTemplate.queryForObject(sql, Integer.class);
        } catch (DataAccessException e) {
            return 0;
        }
    }
}
