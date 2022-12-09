package com.icboluo.controller;

import com.icboluo.entity.FundAsyncRecord;
import com.icboluo.service.FundAsyncRecordService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * (FundAsyncRecord)表控制层
 *
 * @author icboluo
 * @since 2021-06-08 00:09:34
 */
@RestController
@RequestMapping("/fundAsyncRecord")
public class FundAsyncRecordController {
    /**
     * 服务对象
     */
    @Resource
    private FundAsyncRecordService fundAsyncRecordService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne")
    public FundAsyncRecord selectOne(String id) {
        return this.fundAsyncRecordService.queryById(id);
    }

}
