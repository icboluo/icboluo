package com.icboluo.thread;

import com.icboluo.util.ThreadUtil;

import java.util.concurrent.Callable;

/**
 * @author icboluo
 * @since 2022-05-21 13:27
 */
class Call implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        new ThreadUtil().sleep5s();
        return 1;
    }
}
