package com.icboluo.mapper;

import com.icboluo.common.MyBaseMapper;
import com.icboluo.entity.CultivationCareer;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * 修仙生涯(CultivationCareer)表数据库访问层
 *
 * @author icboluo
 * @since 2022-03-15 00:50:25
 */
public interface CultivationCareerMapper extends MyBaseMapper<CultivationCareer> {

    /**
     * 查询指定行数据
     *
     * @param cultivationCareer 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<CultivationCareer> queryAllByLimit(CultivationCareer cultivationCareer, @Param("pageable") Pageable pageable);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<CultivationCareer> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<CultivationCareer> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<CultivationCareer> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<CultivationCareer> entities);

    List<CultivationCareer> selectByPlayer(Integer id);
}

