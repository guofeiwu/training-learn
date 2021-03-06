package com.guofei.wu.juc;


import java.util.concurrent.TimeUnit;

/**
 * @version v1.0
 * @since 2018/12/25
 */
public class TestProductorAndConsumer {
    public static void main(String[] args) {
        Clerk clerk = new Clerk();
        Productor productor = new Productor(clerk);
        Consumer consumer = new Consumer(clerk);
        new Thread(productor, "生产者A").start();
        new Thread(consumer, "消费者B").start();

        new Thread(productor, "生产者C").start();
        new Thread(consumer, "消费者D").start();
    }
}


class Clerk {

    private int product = 0;

    public synchronized void get() {
        while (product >= 1) {
            System.out.println("商品已满！");
            try {
                this.wait();
            } catch (InterruptedException e) {
            }
        }
        System.out.println(Thread.currentThread().getName() + " ：" + ++product);
        this.notifyAll();
    }

    public synchronized void sale() {// consumer 0
        while (product <= 0) {
            System.out.println("缺货！");
            try {
                this.wait();
            } catch (InterruptedException e) {
            }
        }
        System.out.println(Thread.currentThread().getName() + " ：" + --product);
        this.notifyAll();
    }
}

// 生产者
class Productor implements Runnable {
    private Clerk clerk;

    public Productor(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            try {
                TimeUnit.MILLISECONDS.sleep(150);
            } catch (InterruptedException e) {
            }
            clerk.get();
        }
    }
}


// 消费者
class Consumer implements Runnable {

    private Clerk clerk;

    public Consumer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            clerk.sale();
        }
    }
}








