package com.icboluo.mapper;

import com.icboluo.entity.base.Unit;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UnitMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Unit record);

    int insertSelective(Unit record);

    Unit selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Unit record);

    int updateByPrimaryKey(Unit record);

    List<Unit> selectAll();

    List<Unit> selectByCode(String code);
}
