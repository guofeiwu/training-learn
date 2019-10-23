package com.guofei.wu.weekeight.datastructure.queue.blockqueue;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Author Mason
 * @Description
 * @Date 2019/10/12 9:14
 **/
public class BlockQueueTest {

    private static BlockingQueue queue = null;

    @Before
    public void testBefore() {
        queue = new ArrayBlockingQueue(3);
    }


    /**
     * true
     * true
     * true
     * java.lang.IllegalStateException: Queue full
     */
    @Test
    public void testAdd() {
        System.out.println(queue.add(1));
        System.out.println(queue.add(2));
        System.out.println(queue.add(3));
        System.out.println(queue.add(4));
    }


    /**
     * 结果
     * true
     * true
     * true
     * false
     */
    @Test
    public void testOffer() {
        System.out.println(queue.offer(1));
        System.out.println(queue.offer(2));
        System.out.println(queue.offer(3));
        System.out.println(queue.offer(4));
    }


    @Test
    public void testPutAndTake() {
        // Put and Take 会阻塞若是容量不足或者没有元素
    }

    @Test
    public void testRemove() throws Exception {

        System.out.println(queue.offer("user"));
        System.out.println(queue.offer("user"));
        System.out.println(queue.offer(2));

        System.out.println(queue.remove("user"));
        System.out.println(queue.remove(3));
        Iterator iterator = queue.iterator();
        while (iterator.hasNext()) {
            System.out.println("next: " + iterator.next());
        }
    }

    public static void main(String[] args) {
        BlockingQueue queue = new ArrayBlockingQueue(1);
        new Thread(new Producer(queue)).start();
        new Thread(new Producer(queue)).start();
        new Thread(new Consumer(queue)).start();
        new Thread(new Consumer(queue)).start();
    }


    @Test
    public void testElementAndPeek() {
        BlockingQueue queue = new ArrayBlockingQueue(3);
//        System.out.println(queue.offer(1));
//        System.out.println(queue.offer(2));
//        System.out.println(queue.offer(3));


        System.out.println(queue.peek());
        System.out.println(queue.element());

        Iterator iterator = queue.iterator();
        while (iterator.hasNext()) {
            System.out.println("next: " + iterator.next());
        }
    }


    @Test
    public void testDrainTo() {
        BlockingQueue queue = new ArrayBlockingQueue(3);
        System.out.println(queue.offer(1));
        System.out.println(queue.offer(2));
        System.out.println(queue.offer(3));
        List<Integer> list = new ArrayList<>();
        queue.drainTo(list, 2);
        for (Integer integer : list) {
            System.out.println(integer);
        }

    }


}


class Producer implements Runnable {

    private BlockingQueue queue;


    public Producer(BlockingQueue queue) {
        this.queue = queue;
    }


    @Override
    public void run() {
        while (true) {
            try {
                queue.put(1);
                System.out.println("生产:" + 1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


class Consumer implements Runnable {

    private BlockingQueue queue;

    public Consumer(BlockingQueue queue) {
        this.queue = queue;
    }


    @Override
    public void run() {
        while (true) {
            try {
                TimeUnit.SECONDS.sleep(1);
                Integer take = (Integer) queue.take();
                System.out.println("消费:" + take);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


