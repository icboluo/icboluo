package com.icboluo.controller;

import com.icboluo.object.dataobject.UnitDO;
import com.icboluo.service.UnitService;
import com.icboluo.util.response.R;
import com.icboluo.util.response.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author icboluo
 * @date 2021-10-01 22:10
 */
@RestController
@RequestMapping("/unit")
public class UnitController {

    @Resource
    private UnitService unitService;

    @GetMapping("getAll")
    public Response getAll() {
        List<UnitDO> all = unitService.getAll();
        return R.correct(all);
    }

    @GetMapping("selectByCode")
    public Response selectByCode(@RequestParam String code) {
        List<UnitDO> all = unitService.selectByCode(code);
        return R.correct(all);
    }
}
