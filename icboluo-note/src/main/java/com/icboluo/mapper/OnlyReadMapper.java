package com.icboluo.mapper;


import com.icboluo.entity.note.OnlyReadDO;

/**
 * @author icboluo
 */
public interface OnlyReadMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OnlyReadDO record);

    int insertSelective(OnlyReadDO record);

    OnlyReadDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OnlyReadDO record);

    int updateByPrimaryKey(OnlyReadDO record);
}
