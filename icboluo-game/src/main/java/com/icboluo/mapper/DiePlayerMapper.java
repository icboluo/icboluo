package com.icboluo.mapper;

import com.icboluo.common.MyBaseMapper;
import com.icboluo.entity.DiePlayer;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * 玩家(DiePlayer)表数据库访问层
 *
 * @author icboluo
 * @since 2022-03-14 23:11:11
 */
public interface DiePlayerMapper extends MyBaseMapper<DiePlayer> {

    /**
     * 查询指定行数据
     *
     * @param diePlayer 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<DiePlayer> queryAllByLimit(DiePlayer diePlayer, @Param("pageable") Pageable pageable);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<DiePlayer> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<DiePlayer> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<DiePlayer> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<DiePlayer> entities);
}

