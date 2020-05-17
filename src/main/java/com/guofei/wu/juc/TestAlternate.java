package com.guofei.wu.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @version v1.0
 * @since 2018/12/25
 */
public class TestAlternate {

    public static void main(String[] args) {
        AltegnateDemo demo = new AltegnateDemo();

        new Thread(() -> {
            for (int i = 1; i <= 20; i++) {
                demo.loopA(i);
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 1; i <= 20; i++) {
                demo.loopB(i);
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 1; i <= 20; i++) {
                demo.loopC(i);
            }
        }, "C").start();
    }


}


class AltegnateDemo {

    private int number = 1;
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();


    public void loopA(int totalLoop) {
        lock.lock();
        try {

            if (number != 1) {
                condition1.await();
            }
            number = 2;
            System.out.println("A\t" + totalLoop);
            condition2.signal();
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }

    public void loopB(int totalLoop) {
        lock.lock();
        try {

            if (number != 2) {
                condition2.await();
            }
            number = 3;
            System.out.println("B\t" + totalLoop);
            condition3.signal();
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }


    public void loopC(int totalLoop) {
        lock.lock();
        try {
            if (number != 3) {
                condition3.await();
            }
            number = 1;
            System.out.println("C\t" + totalLoop);
            condition1.signal();
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }


}
