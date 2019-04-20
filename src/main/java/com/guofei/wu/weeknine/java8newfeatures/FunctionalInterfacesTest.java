package com.guofei.wu.weeknine.java8newfeatures;

import com.guofei.wu.weeknine.sort.javasortutil.User;
import org.junit.Test;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.*;
import java.util.stream.Stream;

/**
 * lambda 表达式需要函数式接口的支持
 * 函数式接口:接口中只有一个抽象方法的接口
 * Conceptually, a functional interface has exactly one abstract method.
 *
 * @author Mason
 * @author Mason
 * @version 2018/8/2
 * @see FunctionalInterface
 * @since 2018/8/2
 */
public class FunctionalInterfacesTest {

    @Test
    public void test() {
        FunctionalInterfaces<Integer, String> fi = (t) -> String.valueOf(t);
        String value = fi.convert(123);
        System.out.println(value);
    }


    /**
     * 无输入,有返回值
     * 供给型
     *
     * @return void
     * @author Mason
     * @since 2018/8/2
     */
    @Test
    public void supplier() {

        // 获取一个值
        Supplier s = () -> Math.random() * 100;
        print(s.get());

        Supplier<User> user = () -> new User(12);
        print(user.get());

        print("====================================");
        // 构造器引用
        Supplier<User> userSupplier = User::new;
        print(userSupplier.get());
        print("=====================================");
        // 对象的引用::实例方法
        User user1 = new User(29);
        Supplier<String> name = user1::getName;
        print(name.get());

    }


    private void print(Object value) {
        System.out.println("value= " + value);
    }


    /**
     * 输入参数，无返回值
     * 消费型
     *
     * @return void
     * @author Mason
     * @since 2018/8/2
     */
    @Test
    public void consumer() {
        Consumer<Integer> consumer = (i) -> System.out.println(i);
        consumer.accept(8);
    }


    /**
     * 输入参数，返回boolean
     * 断言型
     *
     * @author Mason
     * @since 2018/8/2
     */
    @Test
    public void predicates() {
        Predicate<Integer> p = (t) -> t == 5;
        boolean b = p.test(5);
        print(b);
    }


    /**
     * 输入Integer ，返回User类型
     * 函数型
     *
     * @author Mason
     * @since 2018/8/2
     */
    @Test
    public void functions() {
        // 一个参数，会使用对应的类型的一个参数的构造函数
        // 类名 :: new  构造器的参数列表，需要与函数式接口中参数列表保持一致！
        Function<Integer, User> f = User::new;
        User u = f.apply(20);
        print(u);

        BiFunction<Integer, String, User> f1 = User::new;
        User u1 = f1.apply(1, "jack");
        print(u1);

        BiFunction<Integer, String, Integer> integerStringIntegerBiFunction = f1.andThen(User::getId);
        print(integerStringIntegerBiFunction.apply(1, "jack"));

        print("===============================");
        Function<String, Integer> toInteger = (t) -> Integer.valueOf(t);
        toInteger = Integer::valueOf;
        Function<String, String> backToString = toInteger.andThen(String::valueOf);
        print(backToString.apply("123"));

    }

    @Test
    public void comparator() {
//        Comparator<User> c = (u1, u2) -> u1.getAge().compareTo(u2.getAge());
        // 类名::实例方法
        Comparator<User> a = Comparator.comparing(User::getAge);
        print(a.compare(new User(3), new User(2)));

        print("==============================");

        Comparator<Integer> a1 = (i1, i2) -> Integer.compare(1, 2);
        // 类名:: 静态方法
        a1 = Integer::compare;
    }


    @Test
    public void testDebugger() {
        List<String> list = new LinkedList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        Stream<String> stream = list.stream();
        stream.forEach(System.out::println);
    }
}
