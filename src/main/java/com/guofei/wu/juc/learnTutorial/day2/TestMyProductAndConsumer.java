package com.guofei.wu.juc.learnTutorial.day2;

import org.omg.CORBA.TIMEOUT;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestMyProductAndConsumer {
    public static void main(String[] args) {
        MyClerk myClerk = new MyClerk();
        new Thread(new MyProductor(myClerk), "生产者A").start();
        new Thread(new MyConsumer(myClerk), "消费者B").start();
    }


}


class MyClerk {
    private int product = 0;

    private Lock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();


    public void get() {
        lock.lock();
        try {
            if (product >= 1) {
                System.out.println("货品已满！");
                try {
                    condition.await();
                } catch (InterruptedException e) {
                }
            }
            System.out.println(Thread.currentThread().getName() + ":" + ++product);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }


    public void sale() {
        lock.lock();
        try {
            if (product <= 0) {
                System.out.println("缺货!");
                try {
                    condition.await();
                } catch (InterruptedException e) {
                }
            }
            System.out.println(Thread.currentThread().getName() + ":" + --product);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}


class MyProductor implements Runnable {

    private MyClerk clerk;

    public MyProductor(MyClerk myClerk) {
        this.clerk = myClerk;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
            }
            clerk.get();
        }
    }
}


class MyConsumer implements Runnable {
    private MyClerk clerk;

    public MyConsumer(MyClerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            clerk.sale();
        }
    }
}

