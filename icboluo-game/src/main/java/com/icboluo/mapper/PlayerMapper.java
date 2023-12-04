package com.icboluo.mapper;

import com.icboluo.common.MyBaseMapper;
import com.icboluo.entity.Player;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * 玩家(Player)表数据库访问层
 *
 * @author makejava
 * @since 2022-03-13 03:34:42
 */
public interface PlayerMapper extends MyBaseMapper<Player> {

    /**
     * 查询指定行数据
     *
     * @param player 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<Player> queryAllByLimit(Player player, @Param("pageable") Pageable pageable);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Player> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Player> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Player> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Player> entities);

    void slInsert(Player player);
}

