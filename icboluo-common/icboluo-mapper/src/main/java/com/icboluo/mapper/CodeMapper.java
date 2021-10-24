package com.icboluo.mapper;

import com.icboluo.common.MyBaseMapper;
import com.icboluo.entity.Student;

import java.util.List;

/**
 * @author icboluo
 * @date 2021-10-24 21:28
 */
public interface CodeMapper extends MyBaseMapper<Student> {

    Student selectByCode(int code);

    List<Student> selectByCodes(List<Integer> code);
}
