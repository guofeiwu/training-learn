package com.guofei.wu.juc.tutorial;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author Mason
 * @version v3.0
 * @date 2019-05-05 14:28
 * @since v3.0
 */
public class MainTest {


    public static void main(String[] args) {
//        countDownLatchDemo();
//        cyclicBarrierDemo();
        semaphoreDemo();

    }

    private static void semaphoreDemo() {
        Semaphore semaphore = new Semaphore(5);

        for (int i = 0; i < 8; i++) {
            final int j = i;
            new Thread(() -> {
                try {
                    semaphore.acquire(1);

                    System.out.println(j + " income...");
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(j + "leave...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }

            }, "name: " + i).start();
        }
    }

    private static void cyclicBarrierDemo() {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, () -> {
            System.out.println("呵呵哒");
        });

        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                    try {
                        cyclicBarrier.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, "name: " + i).start();
//            new Thread(() -> {
//                System.out.println(Thread.currentThread().getName());
//                try {
//                    cyclicBarrier.await();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }, "name: " + i).start();
        }
    }

    private static void countDownLatchDemo() {
        CountDownLatch countDownLatch = new CountDownLatch(5);

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName());
                countDownLatch.countDown();
            }, "name: " + i).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
        }
        System.out.println(Thread.currentThread().getName());
    }
}
