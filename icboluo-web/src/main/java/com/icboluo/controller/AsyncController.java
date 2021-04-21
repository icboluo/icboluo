package com.icboluo.controller;

import com.icboluo.common.ResponseResult;
import com.icboluo.service.AsyncService;
import com.icboluo.util.IcBoLuoException;
import com.icboluo.util.response.R;
import com.icboluo.util.response.Response;
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
 * @date 2021-27-19 21:27
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
    public Response sameClassTransfer() {
        excludeException();
        return R.correct();
    }

    @GetMapping("/sameSubclassTransfer")
    public Response sameSubclassTransfer() {
        asyncService.linkTransferException();
        return R.correct();
    }

    @Async
    public void excludeException() {
        throw new IcBoLuoException();
    }
}
