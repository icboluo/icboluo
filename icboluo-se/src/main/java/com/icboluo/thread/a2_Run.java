package com.icboluo.thread;

import com.icboluo.util.ThreadUtil;

/**
 * @author icboluo
 * @since 2022-05-21 13:26
 */
class a2_Run implements Runnable {
    @Override
    public void run() {
        ThreadUtil.sleep5s();
    }
}
