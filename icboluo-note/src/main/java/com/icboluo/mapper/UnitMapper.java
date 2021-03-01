package com.icboluo.mapper;

import com.icboluo.object.dataobject.UnitDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UnitMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UnitDO record);

    int insertSelective(UnitDO record);

    UnitDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UnitDO record);

    int updateByPrimaryKey(UnitDO record);

    List<UnitDO> selectAll();

    List<UnitDO> selectByCode(String code);
}