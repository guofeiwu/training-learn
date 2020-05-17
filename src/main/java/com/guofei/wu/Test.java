package com.guofei.wu;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author Mason
 * @version v1.0
 * @since 2019/3/4
 */
public class Test {
    public static void main(String[] args) throws Exception {
        ArrayBlockingQueue queue = new ArrayBlockingQueue(2);
        queue.offer(1);
        System.out.println(queue.offer(2));
//        new Thread(() -> {
//            while (true) {
//                try {
//
//                    queue.put(3);
//                    System.out.println("放入队列");
//
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();

        new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    Object poll = queue.poll(1, TimeUnit.SECONDS);
                    System.out.println("从队列中取出一个数" + poll);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
