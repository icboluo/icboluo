package com.icboluo.controller;

import com.icboluo.util.DateUtil;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * @author icboluo
 * @since 2022-09-23 19:21
 */
public class LocalDateTimeMyBatisType implements TypeHandler<LocalDateTime> {

    @Override
    public void setParameter(PreparedStatement ps, int i, LocalDateTime parameter, JdbcType jdbcType) throws SQLException {

    }

    @Override
    public LocalDateTime getResult(ResultSet rs, String columnName) throws SQLException {
        String str = rs.getString(columnName);
        return DateUtil.toDateTime(str);
    }

    @Override
    public LocalDateTime getResult(ResultSet rs, int columnIndex) throws SQLException {
        return null;
    }

    @Override
    public LocalDateTime getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return null;
    }
}
