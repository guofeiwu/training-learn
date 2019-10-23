package com.guofei.wu.weeknine.java8newfeatures;

import com.guofei.wu.weeknine.sort.javasortutil.MyComparator;
import com.guofei.wu.weeknine.sort.javasortutil.User;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Stream
 *
 * @author Mason
 * @author Mason
 * @version 2018/8/3
 * @since 2018/8/3
 */
public class StreamTest {


    @Test
    public void testCompare() {
        List<User> users = new ArrayList<>();

        users.stream()
                .sorted(Comparator.comparing(User::getAge).thenComparing(User::getName));

        users.stream()
                .sorted(Comparator.comparing(User::getAge).thenComparing(user -> user.getName()).thenComparing(user -> user.getId()));

        users.stream()
                .sorted((Comparator.comparingInt((User user) -> user.getAge())).thenComparing((o1, o2) -> 0));

        users.stream()
                .sorted((Comparator.comparingInt((User o) -> o.getAge())).thenComparing(user -> user.getName()));


        users.stream()
                .sorted(Comparator.comparing(User::getAge).reversed().thenComparing(User::getName));

    }


    private List<String> stringCollection = null;
    private Stream<String> stream = null;

    /**
     * 这不是一个函数式接口，这个可以有效的避免空指针异常
     *
     * @return void
     * @author Mason
     * @since 2018/8/3
     */
    @Test
    public void optional() {
        Optional<Integer> i = Optional.of(10);
        Integer integer = i.filter((a) -> a == 20)
                .orElse(20);
        print(integer);
        i.ifPresent((t) -> print(t));
    }


    /**
     * 创建流的方式有多种，详见Stream.
     *
     * @return void
     * @author Mason
     * @see Stream
     * @since 2018/8/3
     */
    @Test
    public void streamCreateAndFilter() {
        // 串行流
//        Stream<String> stream = stringCollection.stream();
        // 通过 of
        Stream<List<String>> stringCollection = Stream.of(this.stringCollection);
        Iterator<List<String>> iterator = stringCollection.iterator();
        while (iterator.hasNext()) {
            iterator.next().forEach(s -> print(s));
        }

        // 通过迭代器
        Stream<Integer> stream = Stream.iterate(0, i -> i + 1)
                .limit(10);
        stream.forEach(System.out::println);
//
        // 并行流
//        Stream<String> parallelStream = this.stringCollection.parallelStream();

        // filter 过滤数据,返回新的流
//        stringCollection.filter((s) -> s.startsWith("a"))
//                .forEach(System.out::println);

//        Integer[] nums = new Integer[10];
//        Stream<Integer> stream1 = Arrays.stream(nums);
    }


    @Test
    public void streamSort() {
        // 排序
//        stream.sorted().forEach(System.out::println);

//        stream.sorted((s1, s2) -> s2.compareTo(s1)).forEach(System.out::println);
        stream.sorted(String::compareTo).forEach(System.out::println);
    }


    @Test
    public void streamMap() {
//        stream.filter((s) -> s.startsWith("a")).peek((c) -> System.out.println(c)).forEach(System.out::println);
//        Stream.of("one", "two", "three", "four")
//                .filter(e -> e.length() > 3)
//                .peek(e -> System.out.println("Filtered value: " + e))
//                .map(String::toUpperCase)
//                .peek(e -> System.out.println("Mapped value: " + e))
////                .collect(Collectors.toList())
//                .forEach(System.out::println);

//        stream.map((x) -> {
//            User user = new User(x);
//            return user;
//        }).forEach(System.out::println);

        Map<Integer, String> map = new HashMap<>();

        for (int i = 0; i < 10; i++) {
            // 如果对应的key存在value，则返回value,反正返回null
            map.putIfAbsent(i, "val" + i);
        }

        map.forEach((id, val) -> System.out.println(val));

        print(map.computeIfAbsent(9, (m) -> "" + m + "M"));
        print(map.get(3));


        print(map.computeIfPresent(9, (num, val) -> val + num));
        print(map.computeIfAbsent(10, num -> "val00" + num));
    }


    @Test
    public void streamDistinct() {
//        stream.distinct().forEach(System.out::println);

        stream.filter((s) -> s.startsWith("a") || s.startsWith("c"))
                .skip(1)
                .limit(2)
                .forEach(System.out::println);
    }


    @Test
    public void reduce() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Integer sum = list.stream()
                .reduce(0, (x, y) -> x + y);
        print(sum);
    }


    @Before
    public void initDataAndStream() {
        stringCollection = new ArrayList<>();
        stringCollection.add("ddd2");
        stringCollection.add("aaa2");
        stringCollection.add("bbb1");
        stringCollection.add("aaa1");
        stringCollection.add("bbb3");
        stringCollection.add("ccc");
        stringCollection.add("bbb2");
        stringCollection.add("ddd1");
        stringCollection.add("ccc");
        stream = stringCollection.stream();
//        Stream<List<String>> stringCollection = Stream.of(this.stringCollection);
    }


    private void print(Object value) {
        System.out.println("value= " + value);
    }
}
