package com.guofei.wu.juc.learnTutorial.day2;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class TestMyLock {

    public static void main(String[] args) {
        SellTicket st = new SellTicket();
        new Thread(st,"1好窗口").start();
        new Thread(st,"2好窗口").start();
        new Thread(st,"3好窗口").start();
    }


}

class SellTicket implements Runnable {
    private int ticketNum = 100;

    private ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true) {
            lock.lock();
            try {
                try {
                    TimeUnit.MILLISECONDS.sleep(200);
                } catch (InterruptedException e) {
                }
                if (ticketNum > 0) {
                    System.out.println(Thread.currentThread().getName() + "完成售票，余票为：" + --ticketNum);
                }
            } finally {
                lock.unlock();
            }
        }
    }
}
