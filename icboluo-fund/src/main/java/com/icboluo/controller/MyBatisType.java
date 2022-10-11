package com.icboluo.controller;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 * TODO 这个并没有起作用
 * @author icboluo
 * @since 2022-09-23 19:21
 */
@Component
public class MyBatisType implements TypeHandler<LocalDate> {

    @Override
    public void setParameter(PreparedStatement ps, int i, LocalDate parameter, JdbcType jdbcType) throws SQLException {

    }

    @Override
    public LocalDate getResult(ResultSet rs, String columnName) throws SQLException {
        String str = rs.getString(columnName);
        return LocalDate.parse(str);
    }

    @Override
    public LocalDate getResult(ResultSet rs, int columnIndex) throws SQLException {
        return null;
    }

    @Override
    public LocalDate getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return null;
    }
}
