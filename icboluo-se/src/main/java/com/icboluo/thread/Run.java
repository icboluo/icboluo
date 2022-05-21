package com.icboluo.thread;

import com.icboluo.util.ThreadUtil;

/**
 * @author icboluo
 * @since 2022-05-21 13:26
 */
 class Run implements Runnable{
    @Override
    public void run() {
        new ThreadUtil().sleep5s();
    }
}
