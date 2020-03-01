package com.guofei.wu.interview.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author guofei.wu
 * @version v3.0
 * @date 2020/2/22 11:17 下午
 * @since v3.0
 */
public class LockDemo {

    public static void main(String[] args) throws InterruptedException {
        MyResources myResources = new MyResources();
//        for (int i = 1; i <= 10; i++) {
//            new Thread(() -> {
//                for (int j = 0; j < 1000; j++) {
//                    myResources.iPlusPlus();
//                }
//            }).start();
//        }

        for (int i = 0; i < 200; i++) {
            new Thread(myResources).start();
        }
        TimeUnit.SECONDS.sleep(2);
        System.out.println("i value:" + myResources.i);
    }
}

class MyResources implements Runnable {

    Lock lock = new ReentrantLock();
    public int i;


    public void iPlusPlus() {
        lock.lock();
        try {
            i++;
        } finally {
            lock.unlock();
        }
//        i++;
    }

    @Override
    public void run() {
        for (int j = 0; j < 1000; j++) {
            iPlusPlus();
        }

    }
}

