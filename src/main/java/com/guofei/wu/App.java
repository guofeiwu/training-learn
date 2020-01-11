package com.guofei.wu;


import com.google.common.collect.Maps;

import java.util.*;

/**
 * 集合类
 *
 * @since 2018/8/24
 */
public class App {
    public static void main(String[] args) {
        /**
         * ArrayList default capacity 10
         * Object 类型的数组
         * 1.5倍的扩容
         *
         * @since 2018/8/24
         * @param args
         */
//        ArrayList<String> arrayList = new ArrayList();
//        arrayList.add("a");
//        arrayList.add("");
//        arrayList.add(null);
//        arrayList.add(null);
//        for (String s : arrayList) {
//            System.out.println(s);
//        }
//        String b = arrayList.set(0, "B");
//        System.out.println("old data " + b);
//        for (String s : arrayList) {
//            System.out.println(s);
//        }
//        System.out.println("-------------------------------");
//        arrayList.replaceAll((t) -> t + "a");
//        for (String s : arrayList) {
//            System.out.println(s);
//        }
////        System.out.println("-------------------------------");
////        System.out.println(arrayList.removeIf((p) -> p.equals("B") || p.equals("Ba")));
//        System.out.println("----------------------------");
//        ListIterator<String> stringListIterator = arrayList.listIterator();
//        while (stringListIterator.hasNext()) {
//            System.out.println(stringListIterator.next());
//        }


//        /**
//         * node 链表结构 非线程安全
//         *
//         * @since 2018/8/24
//         */
//        LinkedList<String> linkedList = new LinkedList();
//        linkedList.add("A");
//        linkedList.add("B");
//        for (String s : linkedList) {
//            System.out.println(s);
//        }


        /**
         *
         * Object 数组  线程安全 默认初始化容量为10
         *
         * @since 2018/8/24
         */
//        Vector<String> vector = new Vector<>();
//        vector.add("A");
//        vector.add("B");
//
//        for (String s : vector) {
//            System.out.println(s);
//        }
//        Enumeration<String> elements = vector.elements();
//        while (elements.hasMoreElements()) {
//            System.out.println(elements.nextElement());
//        }
//        System.out.println("----------------------");

//        vector = new Stack<>();


        /**
         * HashSet 内部的实现就是HashMap  HashMap 默认容量为16
         * @since 2018/8/24
         */
//        Set<String> set = new HashSet();
//        set.add("   ");
//        set.add("A");
//        set.add("B");
//        set.add("C");
//        set.add("A");
//        set.add(null);
//        set.add(null);
//        for (String s : set) {
//            System.out.println(s);
//        }
        // 继承HashSet 仅重写了spliterator方法
//        Set<String> linkedHashSet = new LinkedHashSet<>();
//        set = new TreeSet<>();
        /**
         * 初始化容量 11   线程安全  方法加了锁  synchronized
         *
         * @since 2018/8/24
         */
//        Hashtable<String, String> hashtable = new Hashtable<>();
//        hashtable.put("a", "A");
//        hashtable.put("d", "D");
//        hashtable.put("b", "B");
//        hashtable.put("c", "C");
//        hashtable.put("b", "BB");
//        Enumeration<String> elements = hashtable.elements();
//        while (elements.hasMoreElements()) {
//            System.out.println(elements.nextElement());
//        }
//        hashtable.forEach((k, v) -> System.out.println(k + v));
//        System.out.println("---------------------");
//        Set<String> keySet = hashtable.keySet();
//        for (String k : keySet) {
//            System.out.println(k);
//        }
//        System.out.println("-----------------");
//        Set<Map.Entry<String, String>> entries = hashtable.entrySet();
//        Iterator<Map.Entry<String, String>> iterator = entries.iterator();
//        while (iterator.hasNext()) {
//            System.out.println(iterator.next().getKey() + iterator.next().getValue());
//        }

        /**
         *
         * Node<K,V>[] 数组，若数组会空怎么使用时会创建大小是16的Node数组，用15 & key的hash值获取当前key的下标
         * 若所在下标不存在数据，则生成一个新Node，若有数据则分三种情况：
         * 一、去判断hash值，key，value是不是都一致，若hash值，key，value都一致则表示是条相同的数据，最后直接返回旧值。
         * 二、是不是树
         * 三、单链表，查找有没有hash，key，value都一样的数据，若有直接返回旧值，
         * 若没有则将其加到该单链表的最后，当单链表的长度大于等于8之后转化为树
         *
         * @since 2018/8/24
         */
//        Map hashMap = new HashMap();
//
//
//        hashMap.put(null, "A");
//        hashMap.put(1, "B");
//        hashMap.put(2, null);
//        hashMap.put(3, null);
//        hashMap.forEach((b, c) -> {
//            System.out.println(b+"HH");
//            System.out.println(c);
//            System.out.println("------");
//        });

//        // 15 & key的hash值 值相同
//        hashMap.put("A", "A");
//        hashMap.put("34", "34");
//
//        hash("A");
//
//
//        int h = "A".hashCode();
//        System.out.println(h);
//        System.out.println(h >>> 16);
//        System.out.println(h ^ 0);
//        System.out.println(hash("A"));


//        Map<String, Integer> hashMap = Maps.newHashMap();
//        Map<String, Integer> treeMap = Maps.newTreeMap();
//        Map<String, Integer> linkedHashMap = Maps.newLinkedHashMap();
//        System.out.println("--------------test hashMap");
//        testMap(hashMap);
//        System.out.println("--------------test treeMap");
//        testMap(treeMap);
//        System.out.println("--------------test linkedHashMap");
//        testMap(linkedHashMap);


    }

    private static void testMap(Map<String, Integer> map) {
        map.put("asd", 1);
        map.put("2das", 2);
        map.put("3das", 3);
        map.put("4das", 4);
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }
//    static final int hash(Object key) {
//        int h;
//        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
//    }
}
