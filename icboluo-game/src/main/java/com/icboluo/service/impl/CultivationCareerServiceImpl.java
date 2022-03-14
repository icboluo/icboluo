package com.icboluo.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.icboluo.entity.CultivationCareer;
import com.icboluo.mapper.CultivationCareerMapper;
import com.icboluo.service.CultivationCareerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 修仙生涯(CultivationCareer)表服务实现类
 *
 * @author icboluo
 * @since 2022-03-15 00:50:25
 */
@Service
public class CultivationCareerServiceImpl implements CultivationCareerService {
    @Resource
    private CultivationCareerMapper cultivationCareerMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public CultivationCareer queryById(Integer id) {
        return cultivationCareerMapper.queryById(id);
    }

    @Override
    public PageInfo<CultivationCareer> cultivationCareer(Integer id) {
        PageHelper.startPage(1, 10);
        List<CultivationCareer> list = cultivationCareerMapper.selectByPlayer(id);
        return PageInfo.of(list);
    }
}
