package com.icboluo.mapper;

import com.icboluo.object.dataobject.SoftwarePasswordDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SoftwarePasswordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SoftwarePasswordDO record);

    int insertSelective(SoftwarePasswordDO record);

    SoftwarePasswordDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SoftwarePasswordDO record);

    int updateByPrimaryKey(SoftwarePasswordDO record);
}