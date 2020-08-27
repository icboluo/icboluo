package com.icboluo.dao;

import com.icboluo.object.dataobject.RowDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author icboluo
 */
@Repository
public interface ColumnMapper {

    /**
     * 查询表结构
     *
     * @param database 库名
     * @param tableName   表名
     * @return 各个字段的属性
     */
    List<RowDO> selectTableConstruction(
            @Param("database") String database, @Param("tableName") String tableName);

    /**
     * 查询所有的表名
     *
     * @param database 库名
     * @return 表名集合
     */
    List<String> selectTableNameByDatabase(String database);

    /**
     * 查询库结构
     *
     * @param database 库名
     * @param tables      表名集合
     * @return 各个字段的属性
     */
    List<RowDO> selectDatabaseConstruction(
            @Param("database") String database, @Param("tables") List<String> tables);
}
