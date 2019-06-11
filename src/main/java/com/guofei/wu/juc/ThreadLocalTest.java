package com.guofei.wu.juc;

import java.lang.Thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author guofei.wu ()
 * @version v3.0
 * @date 2019-05-12 20:32
 * @since v3.0
 */
public class ThreadLocalTest {
    // Atomic integer containing the next thread ID to be assigned
    private static final AtomicInteger nextId = new AtomicInteger(0);

    // Thread local variable containing each thread's ID
    private static final ThreadLocal<Integer> threadId = ThreadLocal.withInitial(() ->
            nextId.getAndIncrement()
    );

    // Returns the current thread's unique ID, assigning it if necessary
    public static int get() {
        return threadId.get();
    }

    public static void main(String[] args) {
        new Thread(() -> {
            threadId.set(100);
        }, "ABC").start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t" + get());
        }, "A").start();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t" + get());
        }, "B").start();

    }


}
