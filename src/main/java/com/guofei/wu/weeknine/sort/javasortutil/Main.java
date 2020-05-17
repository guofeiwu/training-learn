package com.guofei.wu.weeknine.sort.javasortutil;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author Mason
 * @author Mason
 * @version 2018/8/1
 * @since 2018/8/1
 */
public class Main {


    public static void main(String[] args) {

        List<Student> students = new ArrayList<>();
        students.add(new Student(20));
        students.add(new Student(22));
        students.add(new Student(10));
        students.add(new Student(90));
        Collections.sort(students);
        for (Student s :
                students) {
            System.out.println(s);

        }


        new Thread(() -> {
            System.out.println("A");
        }).start();

        ThreadPoolExecutor MyThread = new ThreadPoolExecutor(4, 10, 10, TimeUnit.SECONDS, new ArrayBlockingQueue(2));

        MyThread.execute(() -> {
            System.out.println("A");
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("A");
            }
        }).start();

    }


    @Test
    public void test2() {
        Consumer<String> con = (x) -> System.out.println(x);
        con.accept("有一个参数，并且无返回值");
    }

    //需求：产生指定个数的整数，并放入集合中
    public List<Integer> getNumList(int num, Supplier<Integer> sup) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Integer n = sup.get();
            list.add(n);
        }
        return list;
    }

    @Test
    public void supplier() {
        List<Integer> numList = getNumList(10, () -> (int) (Math.random() * 100));

        numList = getNumList(10, new Supplier<Integer>() {
            @Override
            public Integer get() {
                return null;
            }
        });
        for (Integer num : numList) {
            System.out.println(num);
        }
    }


    @Test
    public void test() {

        User user = new User(20);
        Supplier<Integer> sup2 = user::getAge;
        System.out.println(sup2.get());


    }

    @Test
    public void test8() {
        Function<Integer, String[]> fun = (args) -> new String[args];
        String[] strs = fun.apply(10);
        System.out.println(strs.length);

        System.out.println("--------------------------");

        Function<Integer, User[]> fun2 = User[]::new;
        User[] emps = fun2.apply(20);
        System.out.println(emps[0]);
    }


}
