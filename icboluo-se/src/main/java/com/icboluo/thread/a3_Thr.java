package com.icboluo.thread;

import com.icboluo.util.SimpleThreadUtil;

/**
 * @author icboluo
 * @since 2022-05-21 13:27
 */
 class a3_Thr extends Thread {
    @Override
    public void run() {
        SimpleThreadUtil.sleep5s();
    }
}
