package com.icboluo.mapper;

import com.icboluo.common.MyBaseMapper;
import com.icboluo.entity.Province;
import org.springframework.cache.annotation.Cacheable;

import java.io.Serializable;
import java.util.List;

/**
 * (Province)表数据库访问层
 *
 * @author icboluo
 * @since 2021-09-24 20:25:17
 */
public interface ProvinceMapper extends MyBaseMapper<Province> {

    /**
     * 查询指定数据
     *
     * @param province 查询条件
     * @return 对象列表
     */
    List<Province> queryByAllField(Province province);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Province> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(List<Province> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Province> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(List<Province> entities);

    @Cacheable(cacheNames = "province:info", key = "#p0")
    default Province cacheQueryById(Serializable id) {
        return queryById(id);
    }

    int insertProvince(Province province);
    int updateProvince(Province province);

    List<Province> selectProvince(Province province);

    List<Province> queryByTableName(String tableName);
}

