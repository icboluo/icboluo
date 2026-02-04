package com.icboluo.service.impl;

import com.icboluo.common.redis.RedisList;
import com.icboluo.entity.Province;
import com.icboluo.mapper.ProvinceMapper;
import com.icboluo.service.ProvinceService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (Province)表服务实现类
 *
 * @author icboluo
 * @since 2021-09-24 20:25:18
 */
@Service
public class ProvinceServiceImpl implements ProvinceService {
    @Resource
    private ProvinceMapper provinceMapper;
    @Resource
    private RedisList<Province> redisList;

    /**
     * 通过ID查询单条数据
     *
     * @param proId 主键
     * @return 实例对象
     */
    @Override
    public Province queryById(Integer proId) {
        return provinceMapper.cacheQueryById(proId);
    }

    @Override
    public List<Province> selectAll() {
        String key = "province:all";
        List<Province> provinces = provinceMapper.selectList(null);
        redisList.del(key);
        redisList.addAll(key, provinces);
        return provinces;
    }
}
