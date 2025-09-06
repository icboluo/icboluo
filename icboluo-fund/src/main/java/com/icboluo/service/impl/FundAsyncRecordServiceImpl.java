package com.icboluo.service.impl;

import com.icboluo.entity.FundAsyncRecord;
import com.icboluo.mapper.FundAsyncRecordMapper;
import com.icboluo.service.FundAsyncRecordService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * (FundAsyncRecord)表服务实现类
 *
 * @author icboluo
 * @since 2021-06-08 00:09:34
 */
@Service
public class FundAsyncRecordServiceImpl implements FundAsyncRecordService {
    @Resource
    private FundAsyncRecordMapper fundAsyncRecordMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public FundAsyncRecord queryById(String id) {
        return this.fundAsyncRecordMapper.selectById(id);
    }

    /**
     * 新增数据
     *
     * @param fundAsyncRecord 实例对象
     * @return 实例对象
     */
    @Override
    public FundAsyncRecord insert(FundAsyncRecord fundAsyncRecord) {
        this.fundAsyncRecordMapper.insert(fundAsyncRecord);
        return fundAsyncRecord;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.fundAsyncRecordMapper.deleteById(id) > 0;
    }
}
