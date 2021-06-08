package com.icboluo.controller;

import com.github.pagehelper.PageInfo;
import com.icboluo.common.ResponseResult;
import com.icboluo.entity.FundAttention;
import com.icboluo.object.query.FundAttentionQuery;
import com.icboluo.object.vo.FundAttentionVO;
import com.icboluo.service.FundAttentionService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * (FundAttention)表控制层
 *
 * @author icboluo
 * @since 2021-05-27 23:05:01
 */
@RestController
@RequestMapping("/fundAttention")
@CrossOrigin(origins = "*", maxAge = 3600)
@ResponseResult
public class FundAttentionController {
    /**
     * 服务对象
     */
    @Resource
    private FundAttentionService fundAttentionService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne")
    public FundAttention selectOne(String id) {
        return this.fundAttentionService.queryById(id);
    }

    @GetMapping("/init")
    public PageInfo<FundAttentionVO> init(FundAttentionQuery query) {
//        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        List<FundAttentionVO> list = fundAttentionService.init(query);
        return PageInfo.of(list);
    }
}
