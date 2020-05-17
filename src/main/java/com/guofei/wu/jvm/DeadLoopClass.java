package com.guofei.wu.jvm;

/**
 * @author Mason
 * @version v1.0
 * @since 2019/3/6
 */
public class DeadLoopClass {


    static {
        if (true) {
            System.out.println("abc");
            while (true) {

            }
        }
    }


    public static void main(String[] args) {
        Runnable runnable = () -> {
            System.out.println("aaa");
            System.out.println(Thread.currentThread() + "start");
            DeadLoopClass dlc = new DeadLoopClass();
            System.out.println(Thread.currentThread().getName() + "run over");
        };
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        thread1.start();
        thread2.start();
    }


}
