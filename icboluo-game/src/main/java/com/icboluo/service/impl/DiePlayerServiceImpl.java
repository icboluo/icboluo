package com.icboluo.service.impl;

import com.icboluo.entity.DiePlayer;
import com.icboluo.mapper.DiePlayerMapper;
import com.icboluo.service.DiePlayerService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 玩家(DiePlayer)表服务实现类
 *
 * @author icboluo
 * @since 2022-03-14 23:11:11
 */
@Service
public class DiePlayerServiceImpl implements DiePlayerService {
    @Resource
    private DiePlayerMapper diePlayerMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public DiePlayer queryById(Integer id) {
        return diePlayerMapper.selectById(id);
    }
}
