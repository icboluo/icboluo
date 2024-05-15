package com.icboluo.service.impl;

import com.icboluo.mapper.FundConfigWeightMapper;
import com.icboluo.service.FundConfigWeightService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;


/**
 * 基金--配置--权重(FundConfigWeight)表服务实现类
 *
 * @author icboluo
 * @since 2024-05-15 18:41:03
 */
@Service
public class FundConfigWeightServiceImpl implements FundConfigWeightService {
    @Resource
    private FundConfigWeightMapper fundConfigWeightMapper;

}
