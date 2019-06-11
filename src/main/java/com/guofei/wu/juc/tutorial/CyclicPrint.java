package com.guofei.wu.juc.tutorial;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author guofei.wu ()
 * @version v3.0
 * @date 2019-05-07 22:25
 * @since v3.0
 */
public class CyclicPrint {


    public static void main(String[] args) {
        ThreadResources tr = new ThreadResources();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                tr.print5();
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                tr.print10();
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                tr.print15();
            }
        }, "C").start();
    }

}


class ThreadResources {
    private int flag = 1;

    Lock lock = new ReentrantLock();

    Condition conditionA = lock.newCondition();
    Condition conditionB = lock.newCondition();
    Condition conditionC = lock.newCondition();


    public void print5() {
        lock.lock();
        try {
            if (flag != 1) {
                conditionA.await();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println("AA--" + (i + 1));
            }
            flag = 2;
            conditionB.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print10() {

        lock.lock();
        try {
            if (flag != 2) {
                conditionB.await();
            }
            for (int i = 0; i < 10; i++) {
                System.out.println("BB--" + (i + 1));
            }
            flag = 3;
            conditionC.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print15() {

        lock.lock();
        try {
            if (flag != 3) {
                conditionC.await();
            }
            for (int i = 0; i < 15; i++) {
                System.out.println("CC--" + (i + 1));
            }
            flag = 1;
            conditionA.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


}
















