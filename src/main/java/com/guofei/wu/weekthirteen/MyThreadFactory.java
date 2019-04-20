package com.guofei.wu.weekthirteen;


import java.util.concurrent.ThreadFactory;

/**
 * @author Mason
 * @author Mason
 * @version 2018/8/28
 * @since 2018/8/28
 */
public class MyThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, "my thread");
        t.setPriority(Thread.NORM_PRIORITY);
        t.setDaemon(false);
        return t;
    }
}
