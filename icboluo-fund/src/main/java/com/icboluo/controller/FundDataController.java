package com.icboluo.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.icboluo.common.ResponseResult;
import com.icboluo.entity.FundData;
import com.icboluo.object.FundDataVO;
import com.icboluo.object.query.FundDataQuery;
import com.icboluo.service.FundDataService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * (FundData)表控制层
 *
 * @author icboluo
 * @since 2021-05-28 00:12:38
 */
@RestController
@RequestMapping("/fundData")
@CrossOrigin(origins = "*", maxAge = 3600)
@ResponseResult
public class FundDataController {
    /**
     * 服务对象
     */
    @Resource
    private FundDataService fundDataService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne")
    public FundData selectOne(Long id) {
        return this.fundDataService.queryById(id);
    }

    @GetMapping("/init")
    public PageInfo<FundData> init(FundDataQuery query) {
        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        List<FundData> fundDataList = fundDataService.selectByQuery(query);
        return PageInfo.of(fundDataList);
    }

    @GetMapping("cal")
    public FundDataVO cal(String fundId) {
        return fundDataService.cal(fundId);
    }
}
