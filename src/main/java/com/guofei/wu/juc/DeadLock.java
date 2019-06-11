package com.guofei.wu.juc;

/**
 * v
 * 死锁
 *
 * @author Mason
 * @version v3.0
 * @date 2019-05-12 14:57
 * @since v3.0
 */
public class DeadLock {

    private Object left = new Object();
    private Object right = new Object();


    public void leftRight() throws Exception {
        synchronized (left) {
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + "\t 获得锁left");
            synchronized (right) {
                System.out.println("尝试获取right");
            }
        }
    }

    public void rightLeft() throws Exception {
        synchronized (right) {
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + "\t 获得锁right");
            synchronized (left) {
                System.out.println("尝试获取right");
            }
        }
    }


    public static void main(String[] args) {

        DeadLock dl = new DeadLock();
        new Thread(() -> {
            try {
                dl.leftRight();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "A").start();
        new Thread(() -> {
            try {
                dl.rightLeft();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "B").start();
    }
}


