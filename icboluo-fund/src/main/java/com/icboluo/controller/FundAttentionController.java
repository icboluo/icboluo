package com.icboluo.controller;

import com.github.pagehelper.PageInfo;
import com.icboluo.object.query.FundAttentionQuery;
import com.icboluo.object.query.FundWeightQuery;
import com.icboluo.object.vo.FundAttentionVO;
import com.icboluo.object.vo.FundMetricVo;
import com.icboluo.service.FundAttentionService;
import com.icboluo.service.FundDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * (FundAttention)表控制层
 *
 * @author icboluo
 * @since 2021-05-27 23:05:01
 */
@RestController
@RequestMapping("fundAttention")
@RequiredArgsConstructor
public class FundAttentionController {

    private final FundAttentionService fundAttentionService;
    private final FundDataService fundDataService;

    @PostMapping("init")
    public PageInfo<FundAttentionVO> init(@RequestBody FundAttentionQuery query) {
        return fundAttentionService.init(query);
    }

    @PostMapping("delete")
    public void delete(@RequestBody String id) {
        fundAttentionService.delete(id);
    }

    @PostMapping("fundWeight")
    public List<FundMetricVo> fundWeight(@RequestBody FundWeightQuery query) {
        return fundDataService.selectWeight(query);
    }
}
