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
     * 通过实体作为筛选条件查询
     *
     * @param province 实例对象
     * @return 对象列表
     */
    List<Province> queryAllByData(Province province);

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
}

