package com.icboluo.common;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * <p>mybatis plus 默认新增策略是input
 * <p>BaseMapper 是允许重写的，我们假定的认为BaseMapper反射过程中生成了其他方法名的方法
 * <p>MybatisPlus 启动时是有主键检查的，如果没有设置主键会将id字段当成主键，如果也没有id字段才会报警告（必报警告）
 * <p>mybatis plus 的qw的使用：可以迁移到mapper层，这样代码整体比较简洁，并且方便复用
 *
 * @author icboluo
 * @since 2021-36-23 21:36
 */
public interface MyBaseMapper<T> extends BaseMapper<T> {

    /**
     * <p>String类型和自增Integer类型是不一致的：
     * <p>String类型的输入主键，需要在实体类上指定 @TableId(type = IdType.INPUT) 其中小括号里面的内容可以删掉
     * <p>Integer类型的自增主键，需要在实体类上指定 TableId(type = IdType.AUTO) 如果不指定AUTO，接口也不会报错，
     * 只是会产生一个较大的id（因为默认的策略是input），违背了自增属性；
     * 生成的Input id 会随着字段类型长度变化，如果是Long类型，则生成的字段值会较长<p/>
     *
     * @param entity 实体类
     * @return 受影响行数
     */
    default int insertSelective(T entity) {
        return insert(entity);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 受影响行数
     */
    default int deleteByPrimaryKey(Serializable id) {
        return deleteById(id);
    }

    /**
     * 删除（根据ID或实体 批量删除）
     *
     * @param idList 主键ID列表或实体列表(不能为 null 以及 empty)
     * @return 受影响行数
     */
    default int deleteByPrimaryKeys(Collection<? extends Serializable> idList) {
        return deleteBatchIds(idList);
    }

    /**
     * 删除（根据ID或实体 批量删除）
     *
     * @param idList 主键ID列表或实体列表(不能为 null 以及 empty)
     * @return 受影响行数
     */
    default int deleteByIds(Collection<? extends Serializable> idList) {
        return deleteBatchIds(idList);
    }

    /**
     * <p> mybatis-plus 默认实现是not-null 即
     * <p> <if field!=null> do update</if>
     * <p> 注意，只是 not null，并不是 not empty
     *
     * @param entity 实例对象
     * @return 影响行数
     */
    default int updateByPrimaryKeySelective(T entity) {
        return updateById(entity);
    }

    /**
     * 根据 ID 查询
     *
     * @param id 主键ID
     * @return 主键对应的实体
     */
    default T selectByPrimaryKey(Serializable id) {
        return selectById(id);
    }

    /**
     * 根据 ID 查询
     *
     * @param id 主键ID
     * @return 主键对应的实体
     */
    default T queryById(Serializable id) {
        return selectById(id);
    }

    /**
     * 查询（根据ID 批量查询）
     *
     * @param idList 主键ID列表(不能为 null 以及 empty)
     * @return 主键对应的实体集合
     */
    default List<T> selectByIds(Collection<? extends Serializable> idList) {
        return selectBatchIds(idList);
    }

    /**
     * 查询全部记录
     *
     * @return 数据库全部数据集合
     */
    default List<T> selectAll() {
        return selectList(null);
    }

    /**
     * 查询全部记录
     *
     * @return 数据库全部数据集合
     */
    default List<T> queryAll() {
        return selectList(null);
    }

    /**
     * 查询总记录数
     *
     * @return 总记录数
     */
    default long count() {
        return count(Wrappers.emptyWrapper());
    }

    /**
     * 根据 Wrapper 条件，查询总记录数
     *
     * @param queryWrapper 实体对象封装操作类 {@link com.baomidou.mybatisplus.core.conditions.query.QueryWrapper}
     * @return 根据 Wrapper 删选后的总记录数
     */
    private long count(Wrapper<T> queryWrapper) {
        return SqlHelper.retCount(this.selectCount(queryWrapper));
    }
}
