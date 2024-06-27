package com.icboluo.service.impl;

import com.icboluo.entity.Area;
import com.icboluo.mapper.AreaMapper;
import com.icboluo.service.AreaService;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;

/**
 * (Area)表服务实现类
 * JDK取消了对javax的支持，Spring同样也取消了，@Resource 注解已经失效了，需要使用 jakarta.annotation.Resource 该包底下的注解才能生效
 *
 * @author icboluo
 * @since 2023-07-07 06:01:20
 */
@Service
public class AreaServiceImpl implements AreaService {
    @Resource
    private AreaMapper areaMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param areId 主键
     * @return 实例对象
     */
    @Override
    public Area queryById(Integer areId) {
        return areaMapper.queryById(areId);
    }
}
