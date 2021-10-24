package com.icboluo.controller;

import com.github.pagehelper.PageInfo;
import com.icboluo.object.query.FundAttentionQuery;
import com.icboluo.object.vo.FundAttentionVO;
import com.icboluo.service.FundAttentionService;
import com.icboluo.common.ResponseResult;
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

    @Resource
    private FundAttentionService fundAttentionService;

    @GetMapping("/init")
    public PageInfo<FundAttentionVO> init(FundAttentionQuery query) {
//        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        List<FundAttentionVO> list = fundAttentionService.init(query);
        return PageInfo.of(list);
    }

    @GetMapping("/delete")
    public void delete(String id) {
        fundAttentionService.delete(id);
    }
}
