package com.icboluo.mapper;

import com.icboluo.entity.base.SoftwarePasswordDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SoftwarePasswordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SoftwarePasswordDO record);

    int insertSelective(SoftwarePasswordDO record);

    SoftwarePasswordDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SoftwarePasswordDO record);

    int updateByPrimaryKey(SoftwarePasswordDO record);

    /**
     * 批量新增
     *
     * @param list 新增的数据
     */
    void insertBatch(List<SoftwarePasswordDO> list);
}
