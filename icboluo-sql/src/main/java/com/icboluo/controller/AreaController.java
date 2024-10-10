package com.icboluo.controller;

import com.icboluo.entity.Area;
import com.icboluo.mapper.AreaMapper;
import com.icboluo.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;

import java.util.concurrent.CompletableFuture;

/**
 * (Area)表控制层
 *
 * @author icboluo
 * @since 2023-07-07 05:54:32
 */
@RestController
@RequestMapping("area")
public class AreaController {

    @Resource
    private AreaService areaService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public Area queryById(@PathVariable("id") Integer id) {
        return areaService.queryById(id);
    }

    //    事物加到controller层也是有用的
//    子线程无法获取父线程写未提交的数据的时候，
//    可以手动开启事物提交
//            同类的方法之间调用子方法的事物注解不会生效

    @Resource
    private AreaMapper areaMapper;

    @Autowired(required = false)
    private DataSourceTransactionManager dataSourceTransactionManager;

    @Autowired
    private TransactionDefinition transactionDefinition;

    // @Transactional 注解针对的是整个方法做了代理
    public void update(Area db) {
        TransactionStatus a = dataSourceTransactionManager.getTransaction(transactionDefinition);
        try {
            areaMapper.insert(db);
            dataSourceTransactionManager.commit(a);
        } catch (Exception e) {
            dataSourceTransactionManager.rollback(a);
        }
        CompletableFuture.runAsync(() -> {
            Area storageBaseLine = areaMapper.selectById(3956);
            areaMapper.deleteById(3956);
            System.out.println("storageBaseLine = " + storageBaseLine);
        });
        // 如果此处有耗时方法，异步线程可能等不到主线程事物提交，所以不建议使用 @Transactional
    }
}

