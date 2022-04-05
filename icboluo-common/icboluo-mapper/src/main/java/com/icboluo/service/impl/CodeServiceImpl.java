package com.icboluo.service.impl;

import com.icboluo.entity.Student;
import com.icboluo.mapper.CodeMapper;
import com.icboluo.object.ArchivesVO;
import com.icboluo.service.CodeService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author icboluo
 * @since 2021-10-24 21:24
 */
public class CodeServiceImpl implements CodeService {

    @Resource
    private CodeMapper codeMapper;

    @Override
    public int codeToId(int code) {
        Student student = codeMapper.selectByCode(code);
        return student.getId();
    }

    @Override
    public Map<Integer, Integer> codeToId(List<Integer> code) {
        List<Student> students = codeMapper.selectByCodes(code);
        return students.stream()
                .collect(Collectors.toMap(Student::getCode, Student::getId));
    }

    @Override
    public List<Integer> codeToIdList(List<Integer> code) {
        Map<Integer, Integer> codeIdMap = codeToId(code);
        return code.stream()
                .map(codeIdMap::get)
                .toList();
    }

    /**
     * 是否是管理员
     * 用Optional来代替链式null判断
     *
     * @param arch 用户对象
     * @return 是否是管理员
     */
    private boolean isAdmin(ArchivesVO arch) {
        return Optional.ofNullable(arch)
                .map(ArchivesVO::getName)
                .filter("admin"::equals)
                .isPresent();
    }
}
