package com.icboluo;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author icboluo
 * @date 2021-36-23 21:36
 */
@SuppressWarnings("all")
public interface MyBaseMapper<T> extends BaseMapper<T>{
    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    default int deleteByPrimaryKey(Serializable id) {
        return deleteById(id);
    }

    @Override
    int insert(T entity);

    default int insertSelective(T record) {
        return insert(record);
    }

    default T selectByPrimaryKey(Serializable id) {
        return selectById(id);
    }

    default T queryById(Serializable id) {
        return selectById(id);
    }

    default List<T> selectByIds(Collection<? extends Serializable> ids) {
        return selectBatchIds(ids);
    }

    default List<T> selectAll() {
        return selectList(null);
    }

    default List<T> queryAll() {
        return selectList(null);
    }

    /**
     * mybatis-plus 默认实现是not-null 即
     * <if field!=null> do update</if>
     * 注意，只是 not null，并不是 not empty
     *
     * @param record 实例对象
     * @return 影响行数
     */
    default int updateByPrimaryKeySelective(T record) {
        return updateById(record);
    }

    int updateByPrimaryKey(T record);
}
