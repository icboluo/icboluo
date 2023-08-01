package com.icboluo.controller;

import com.icboluo.entity.Province;
import com.icboluo.service.ProvinceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Province)表控制层
 *
 * @author icboluo
 * @since 2021-09-24 20:25:18
 */
@RestController
@RequestMapping("/province")
public class ProvinceController {
    /**
     * 服务对象
     */
    @Resource
    private ProvinceService provinceService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne")
    public Province selectOne(Integer id) {
        return provinceService.queryById(id);
    }

    @GetMapping("/selectAll")
    public List<Province> selectAll() {
        return provinceService.selectAll();
    }

}
