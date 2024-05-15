package com.icboluo.common;

import com.icboluo.util.DateUtil;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 * @author icboluo
 * @since 2022-09-23 19:21
 */
public class LocalDateMyBatisType implements TypeHandler<LocalDate> {

    @Override
    public void setParameter(PreparedStatement ps, int i, LocalDate parameter, JdbcType jdbcType) throws SQLException {
        ps.setObject(i, parameter);
    }

    @Override
    public LocalDate getResult(ResultSet rs, String columnName) throws SQLException {
        String str = rs.getString(columnName);
        return DateUtil.allToDate(str);
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
