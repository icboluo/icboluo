package com.icboluo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.icboluo.entity.Monster;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 玩家(Monster)表数据库访问层
 *
 * @author makejava
 * @since 2022-03-13 03:01:17
 */
public interface MonsterMapper extends BaseMapper<Monster> {

    /**
     * 查询指定行数据
     *
     * @param monster 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<Monster> queryAllByLimit(Monster monster, @Param("pageable") Pageable pageable);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Monster> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Monster> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Monster> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Monster> entities);
}

