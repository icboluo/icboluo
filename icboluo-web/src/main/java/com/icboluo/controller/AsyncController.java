package com.icboluo.controller;

import com.icboluo.annotation.ResponseResult;
import com.icboluo.service.AsyncService;
import com.icboluo.util.IcBoLuoException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p/>异步执行机制首先开启异步
 * <p/>在需要异步执行的类或方法上开启异步功能
 * <p/>同类直接调用异步功能无效
 * <p/>异步处理流程，异步返回值不影响主流程，只是一个额外的处理
 *
 * @author icboluo
 * @since 2021-27-19 21:27
 */
@RestController
@RequestMapping("/async")
@Slf4j
@ResponseResult
public class AsyncController {

    @Resource
    private AsyncService asyncService;

    @GetMapping("/simple")
    public void simple() {
        asyncService.simpleException();
    }

    @GetMapping("/sameClassTransfer")
    public void sameClassTransfer() {
        excludeException();
    }

    @GetMapping("/sameSubclassTransfer")
    public void sameSubclassTransfer() {
        asyncService.linkTransferException();
    }

    @Async
    public void excludeException() {
        throw new IcBoLuoException();
    }
}
