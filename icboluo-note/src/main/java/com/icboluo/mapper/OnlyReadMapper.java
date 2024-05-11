package com.icboluo.mapper;


import com.icboluo.entity.NoteOnlyRead;

/**
 * @author icboluo
 */
public interface OnlyReadMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NoteOnlyRead record);

    int insertSelective(NoteOnlyRead record);

    NoteOnlyRead selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NoteOnlyRead record);

    int updateByPrimaryKey(NoteOnlyRead record);
}
