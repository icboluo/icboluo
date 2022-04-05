package com.icboluo.service;

import com.icboluo.util.IcBoLuoException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author icboluo
 * @since 2021-28-19 21:28
 */
@Service
@Slf4j
public class AsyncService {
    /**
     * 死循环
     */
    @Async
    public void simpleException() {
        throw new IcBoLuoException();
    }

    /**
     * 链式调用死循环
     */
    @Async
    public void linkTransferException() {
        excludeException();
    }

    public void excludeException() {
        throw new IcBoLuoException();
    }
}
