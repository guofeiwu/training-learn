package com.guofei.wu.juc.learnTutorial.day3;

public class Test {
    public static Object object = new Object();
    public static void main(String[] args) {
        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();
        new Thread(thread1,"A").start();
         
        thread1.start();
         
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(thread2,"B").start();
    }
     
    static class Thread1 extends Thread{
        @Override
        public void run() {
            synchronized (object) {
                try {
                    object.wait();
                } catch (InterruptedException e) {
                }
                System.out.println("线程"+Thread.currentThread().getName()+"获取到了锁");
            }
        }
    }
     
    static class Thread2 extends Thread{
        @Override
        public void run() {
            synchronized (object) {
                object.notify();
                System.out.println("线程"+Thread.currentThread().getName()+"调用了object.notify()");
            }
            System.out.println("线程"+Thread.currentThread().getName()+"释放了锁");
        }
    }
}