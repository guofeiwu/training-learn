package com.guofei.wu.generic;

/**
 * @version v1.0
 * @since 2018/12/24
 */
public class GenericTest<K, T> {

    public void test1(T a) {

    }

    public static <T> int test12(T a) {
        return 0;
    }

    public static <T extends Comparable<T>> int countGreaterThan(T[] anArray, T elem) {
        int count = 0;
        for (T e : anArray)
            if (e.compareTo(elem) > 0)  // compiler error
                ++count;
        return count;
    }


    public  int test2(T t, K k) {

        return (Integer) t + (Integer) k;

    }

    public static void main(String[] args) {
        GenericTest<Integer, Integer> genericTest = new GenericTest<>();
        int i = genericTest.test2(1, 2);
        System.out.println(i);
    }


}
