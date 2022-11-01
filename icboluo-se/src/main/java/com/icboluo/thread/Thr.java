package com.icboluo.thread;

import com.icboluo.util.ThreadUtil;

/**
 * @author icboluo
 * @since 2022-05-21 13:27
 */
 class Thr extends Thread {
    @Override
    public void run() {
        ThreadUtil.sleep5s();
    }
}
