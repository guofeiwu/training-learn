package com.guofei.wu.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @version v1.0
 * @since 2018/12/25
 */
public class TestLock {


    public static void main(String[] agrs) {
        Ticket t = new Ticket();
        new Thread(t, "1号窗口").start();
        new Thread(t, "2号窗口").start();
        new Thread(t, "3号窗口").start();
    }

}

class Ticket implements Runnable {

    private int tick = 100;

    private Lock lock = new ReentrantLock();


    @Override
    public void run() {


        while (true) {
            lock.lock();
            try {
                if (tick > 0) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(150);
                    } catch (InterruptedException e) {
                    }
                    System.out.println(Thread.currentThread().getName() + " 完成售票,声音票数为：" + --tick);
                }
            } finally {
                lock.unlock();
            }
        }


    }
}



