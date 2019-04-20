package com.guofei.wu.weekeight.datastructure.queue.sequence;


import com.guofei.wu.weekeight.datastructure.queue.Node;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * @author guofei.wu
 * @email guofei_wu@ucarinc.com
 * @date 2018/7/24 11:13
 * @description
 */
public class Main {
    @Test
    public void testSequence() {
        SeqList<String> list = new SeqList<String>(3);

        list.add(0, "A");
        list.add(1, "B");
        list.add(2, "C");
        list.add(3, "E");
        list.add(4, "F");
        list.add(1, "D");
        list.add(2, "F");
        for (int i = 0; i < list.length(); i++) {
            System.out.println(list.get(i));
        }

        System.out.println("indexOf F:" + list.indexOf("F"));
        System.out.println("lastIndexOf F:" + list.lastIndexOf("F"));


        System.out.println("==========================size: " + list.length());
        list.remove("F");
        System.out.println("==========================size: " + list.length());
        for (int i = 0; i < list.length(); i++) {
            System.out.println(list.get(i));
        }

        list.clear();
        System.out.println("==========================size: " + list.length());
    }


    @Test
    public void testLinkList() {
        SingleILinkedList<String> linkedList = new SingleILinkedList<String>(new Node<String>("A"));
        linkedList.add("B");
        linkedList.add("C");
        linkedList.add("D");
        linkedList.add("A");
        linkedList.add("E");
        linkedList.add("A");
        linkedList.add("B");
        linkedList.add("A");
        linkedList.add(11111, "a");
        linkedList.add(1, "F");


        System.out.println("length: " + linkedList.length());
        System.out.println("===============================");


        for (int i = 0; i < linkedList.length(); i++) {
            System.out.println(linkedList.get(i));
        }

        System.out.println("------------------------------");
        linkedList.removeAll("A");

        for (int i = 0; i < linkedList.length(); i++) {
            System.out.println(linkedList.get(i));
        }

    }


    @Test
    public void testHeadLinkList() {
        HeadSingleILinkedList<String> linkedList = new HeadSingleILinkedList<String>();
        linkedList.add("B");
        linkedList.add("C");
        linkedList.add("D");
        linkedList.add("A");
        linkedList.add("E");
        linkedList.add("A");
        linkedList.add("B");
        linkedList.add("A");


        for (int i = 0; i < linkedList.length(); i++) {
            System.out.println(linkedList.get(i));
        }
        System.out.println("---------------------------------");


        linkedList.add(2, "F");
        for (int i = 0; i < linkedList.length(); i++) {
            System.out.println(linkedList.get(i));
        }

        System.out.println("--------------------------------------");
//        System.out.println(linkedList.remove(8));

        System.out.println("-----------------------------------");
//        linkedList.set(2, "G");

        linkedList.add(1000, "G");
        for (int i = 0; i < linkedList.length(); i++) {
            System.out.println(linkedList.get(i));
        }

        System.out.println(linkedList.contains("A"));
        System.out.println("------------------------");
        linkedList.removeAll("A");
        for (int i = 0; i < linkedList.length(); i++) {
            System.out.println(linkedList.get(i));
        }

        List<String> a = new ArrayList<>();
        a.add("1");
        a.add("2");
        a.add("3");
        a.stream().forEach(System.out::println);

    }


    @Test
    public void testOne() {
        System.out.println(2 >> 1);
    }


    @Test
    public void testCirculList() {
        CircularHeadSILinkedList<String> list = new CircularHeadSILinkedList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add(1, "D");
        list.add(2, "E");
    }


    @Test
    public void testArray() {
        String[] o = (String[]) Array.newInstance(String.class, 5);
        o[0] = "A";
        o[1] = "B";
        for (int i = 0; i < o.length; i++) {
            System.out.println(o[i]);
        }
        System.out.println("---------------------");


        boolean[] booleans = {true, false, false, true};

        System.out.println(Array.getBoolean(booleans, 2));

        System.out.println("---------------------");
        System.out.println(Array.getLength(booleans));


    }

    int size = 2;

    @Test
    public void testSize() {


        String[] a = {"A", "B", "C", "D", "E"};

        System.out.println(a[size]);


        System.out.println(size);

//        int b = size - 1;
        System.out.println(a[size - 1]);

        System.out.println(size);


    }


}
