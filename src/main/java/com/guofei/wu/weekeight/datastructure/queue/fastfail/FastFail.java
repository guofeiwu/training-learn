package com.guofei.wu.weekeight.datastructure.queue.fastfail;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @author guofei.wu
 * @email
 * @date 2018/7/26 17:00
 * @description 快速失败
 */
public class FastFail {

//    private static List<Integer> list = new ArrayList<>(Arrays.asList(1, 2,3,4,5));

    //    List<String> ls = new CopyOnWriteArrayList();
//
    @Test
    public void testFastFail() {
        ThreadA a = new ThreadA();
        ThreadB b = new ThreadB();
        new Thread(a).start();
        new Thread(b).start();

    }


    class ThreadA implements Runnable {

        @Override
        public void run() {
            int i = 0;
            while (i < 100) {
                list.add(i + "");
//                printAll();
                i++;
            }
        }
    }

    class ThreadB implements Runnable {

        @Override
        public void run() {
//            printAll();
            int i = 0;
            while (i < 100) {
//                list.add(i + "");
                printAll();
                i++;
            }
        }
    }


    private static List<String> list = new ArrayList<>();
//    private static List<String> list = new CopyOnWriteArrayList<>();

    private static void printAll() {
        String value = null;
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            value = (String) iter.next();
            System.out.print(value + ", ");
        }
    }
}
